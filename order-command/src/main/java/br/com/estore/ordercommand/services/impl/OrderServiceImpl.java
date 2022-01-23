package br.com.estore.ordercommand.services.impl;

import br.com.estore.ordercommand.components.publishers.Publisher;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.dtos.PaymentDTO;
import br.com.estore.ordercommand.domain.entities.Order;
import br.com.estore.ordercommand.domain.entities.Status;
import br.com.estore.ordercommand.domain.enums.StatusEnum;
import br.com.estore.ordercommand.repositories.OrderRepository;
import br.com.estore.ordercommand.repositories.StatusRepository;
import br.com.estore.ordercommand.services.OrderService;
import br.com.estore.ordercommand.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final StatusRepository statusRepository;
    private final ModelMapper mapper;
    private final Publisher<OrderDTO> publisher;
    private final PaymentService paymentService;

    public OrderServiceImpl(final OrderRepository repository,
                            final StatusRepository statusRepository,
                            final ModelMapper mapper,
                            final Publisher<OrderDTO> publisher,
                            final PaymentService paymentService) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.mapper = mapper;
        this.publisher = publisher;
        this.paymentService = paymentService;
    }

    @Override
    public OrderDTO create(final OrderDTO order) {
        final Order orderEntity = mapper.map(order, Order.class);
        final Order orderSaved = createNewOrder(orderEntity);
        return publishAndSave(orderSaved);
    }

    @Override
    public OrderDTO confirm(final Long orderId, final PaymentDTO payment) {
        final Order order = repository.findByIdAndStatusCode(orderId, StatusEnum.CREATED.name())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order id: %s not found", orderId)));

        if (paymentService.isValidCard(payment)) {
            final Order orderWaitPayment = updateWaitPaymentOrder(order);
            return publishAndSave(orderWaitPayment);
        }

        throw new IllegalArgumentException("Invalid Credit Card");
    }

    private Order createNewOrder(final Order order) {
        final Status status = statusRepository.findByCode(StatusEnum.CREATED.name());
        return repository.save(order.toBuilder().status(status).published(false).build());
    }

    private Order updateWaitPaymentOrder(final Order order) {
        final Status status = statusRepository.findByCode(StatusEnum.WAIT_PAYMENT.name());
        return repository.save(order.toBuilder().status(status).published(false).build());
    }

    private OrderDTO publishAndSave(final Order order) {
        final OrderDTO orderResponse = mapper.map(order, OrderDTO.class);
        publisher.publish(orderResponse);
        repository.save(order.toBuilder().published(true).build());
        return orderResponse;
    }

}
