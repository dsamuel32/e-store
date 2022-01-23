package br.com.estore.orderquery.services.impl;

import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.domain.entities.Order;
import br.com.estore.orderquery.repositories.OrderRepository;
import br.com.estore.orderquery.repositories.OrderRepositoryCustom;
import br.com.estore.orderquery.repositories.impl.OrderRepositoryCustomImpl;
import br.com.estore.orderquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

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
    private OrderRepositoryCustomImpl orderRepositoryCustom;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldSearchOrders() {
        final Order order = JSONUtil.fromFile("samples/order.json", Order.class);
        final OrderDTO orderDTO = JSONUtil.fromFile("samples/orderDTO.json", OrderDTO.class);
        when(orderRepositoryCustom.search(any(OrderFilterDTO.class))).thenReturn(Collections.singletonList(order));
        when(mapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(orderDTO);
        final OrderFilterDTO filter = OrderFilterDTO.builder().email("teste@teste.com").build();
        final List<OrderDTO> orders = service.search(filter);
        assertFalse(orders.isEmpty());
    }

    @Test
    void shouldSaveOrUpdateOrders() {
        final OrderDTO order = JSONUtil.fromFile("samples/orderDTO.json", OrderDTO.class);
        final Order orderEntity = JSONUtil.fromFile("samples/order.json", Order.class);
        when(mapper.map(any(OrderDTO.class), eq(Order.class))).thenReturn(orderEntity);
        service.process(order);
        verify(orderRepository).save(orderEntity);
    }

}