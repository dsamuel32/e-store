package br.com.estore.ordercommand.services.impl;

import br.com.estore.ordercommand.components.publishers.OrderRabbitMQPublisher;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.dtos.PaymentDTO;
import br.com.estore.ordercommand.domain.entities.Order;
import br.com.estore.ordercommand.domain.entities.Status;
import br.com.estore.ordercommand.domain.enums.StatusEnum;
import br.com.estore.ordercommand.repositories.OrderRepository;
import br.com.estore.ordercommand.repositories.StatusRepository;
import br.com.estore.ordercommand.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for OrderServiceImpl")
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private OrderRepository repository;

    @Mock
    private OrderRabbitMQPublisher publisher;

    @Mock
    private FakeCreditCardPayment fakeCreditCardPayment;


    @Test
    void shouldCreateOrder() {
        final Order entity = JSONUtil.fromFile("samples/orderEntityToSave.json", Order.class);
        final OrderDTO order = JSONUtil.fromFile("samples/orderDTOToSave.json", OrderDTO.class);
        final Order entitySave = JSONUtil.fromFile("samples/orderEntitySaved.json", Order.class);
        final OrderDTO orderResponse = JSONUtil.fromFile("samples/orderDTOSaved.json", OrderDTO.class);

        when(mapper.map(any(OrderDTO.class), eq(Order.class))).thenReturn(entity);
        when(statusRepository.findByCode(anyString())).thenReturn(Status.builder().id(1l).code("CREATED").description("Criada").build());
        when(repository.save(any(Order.class))).thenReturn(entitySave);
        when(mapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderResponse);

        final OrderDTO result = service.create(order);

        assertEquals(orderResponse, result);
        verify(publisher).publish(orderResponse);
        verify(repository).save(entitySave.toBuilder().published(true).build());

    }

    @Test
    void shouldConfirmOrder() {
        final PaymentDTO payment = JSONUtil.fromFile("samples/payment.json", PaymentDTO.class);
        final Order orderCreated = JSONUtil.fromFile("samples/orderEntitySaved.json", Order.class);
        final Order orderWaitPayment = JSONUtil.fromFile("samples/orderEntityWaitPayment.json", Order.class);
        final OrderDTO orderResponseWaitPayment = JSONUtil.fromFile("samples/orderDTOWaitPayment.json", OrderDTO.class);

        when(repository.findByIdAndStatusCode(anyLong(), anyString())).thenReturn(Optional.of(orderCreated));
        when(fakeCreditCardPayment.isValidCard(any(PaymentDTO.class))).thenReturn(true);
        when(statusRepository.findByCode(anyString()))
                .thenReturn(Status.builder().id(2l).code(StatusEnum.WAIT_PAYMENT.name()).description("Aguardando Pagamento").build());
        when(repository.save(any(Order.class))).thenReturn(orderWaitPayment);
        when(mapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderResponseWaitPayment);

        final OrderDTO result = service.confirm(1l, payment);

        assertEquals(orderResponseWaitPayment, result);
        verify(publisher).publish(orderResponseWaitPayment);
        verify(repository).save(orderWaitPayment.toBuilder().published(true).build());

    }

    @Test
    void shouldNotFoundOrderToConfirm() {
        final PaymentDTO payment = JSONUtil.fromFile("samples/payment.json", PaymentDTO.class);
        when(repository.findByIdAndStatusCode(anyLong(), anyString())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> service.confirm(1l, payment));
        verifyNoInteractions(publisher);
        verifyNoInteractions(fakeCreditCardPayment);

    }

    @Test
    void shouldNotConfirmCreditCartValid(){
        final PaymentDTO payment = JSONUtil.fromFile("samples/payment.json", PaymentDTO.class);
        final Order orderCreated = JSONUtil.fromFile("samples/orderEntitySaved.json", Order.class);
        when(repository.findByIdAndStatusCode(anyLong(), anyString())).thenReturn(Optional.of(orderCreated));
        when(fakeCreditCardPayment.isValidCard(any(PaymentDTO.class))).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.confirm(1l, payment));
        verifyNoInteractions(publisher);

    }

}