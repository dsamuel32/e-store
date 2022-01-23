package br.com.estore.ordercommand.services.impl;

import br.com.estore.ordercommand.components.publishers.Publisher;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.entities.Order;
import br.com.estore.ordercommand.domain.entities.Status;
import br.com.estore.ordercommand.domain.enums.StatusEnum;
import br.com.estore.ordercommand.repositories.OrderRepository;
import br.com.estore.ordercommand.repositories.StatusRepository;
import br.com.estore.ordercommand.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final StatusRepository statusRepository;
    private final ModelMapper mapper;
    private final Publisher<OrderDTO> publisher;

    public OrderServiceImpl(final OrderRepository repository,
                            final StatusRepository statusRepository,
                            final ModelMapper mapper,
                            final Publisher<OrderDTO> publisher) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public OrderDTO create(final OrderDTO order) {
        final Order orderEntity = mapper.map(order, Order.class);
        final Order orderSaved = createNewOrder(orderEntity);
        return publishAndSave(orderSaved);
    }

    private Order createNewOrder(final Order order) {
        final Status status = statusRepository.findByCode(StatusEnum.CREATED.name());
        return repository.save(order.toBuilder().status(status).build());
    }

    private OrderDTO publishAndSave(final Order order) {
        final OrderDTO orderResponse = mapper.map(order, OrderDTO.class);
        publisher.publish(orderResponse);
        repository.save(order.toBuilder().published(true).build());
        return orderResponse;
    }

}
