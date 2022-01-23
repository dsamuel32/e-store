package br.com.estore.orderquery.repositories.impl;

import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.domain.entities.Order;
import br.com.estore.orderquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for OrderRepositoryCustomImpl")
@ExtendWith(SpringExtension.class)
class OrderRepositoryCustomImplTest {

    @InjectMocks
    private OrderRepositoryCustomImpl orderRepositoryCustom;

    @Mock
    private MongoTemplate template;

    @Test
    void shouldSearchOrdersAllFilters() {
        final Order order = JSONUtil.fromFile("samples/order.json", Order.class);
        when(template.find(any(), eq(Order.class))).thenReturn(Collections.singletonList(order));
        final OrderFilterDTO filter = OrderFilterDTO.builder()
                .email("teste@teste.com")
                .status("WAIT_PAYMENT")
                .maxPrice(BigDecimal.valueOf(5000.0))
                .minPrice(BigDecimal.valueOf(1000.0))
                .build();

        final List<Order> orders = orderRepositoryCustom.search(filter);
        assertFalse(orders.isEmpty());
        assertTrue(orders.contains(order));
    }

    @Test
    void shouldSearchOrdersWithMinPriceFilters() {
        final Order order = JSONUtil.fromFile("samples/order.json", Order.class);
        when(template.find(any(), eq(Order.class))).thenReturn(Collections.singletonList(order));
        final OrderFilterDTO filter = OrderFilterDTO.builder()
                .email("teste@teste.com")
                .status("WAIT_PAYMENT")
                .minPrice(BigDecimal.valueOf(5000.0))
                .build();

        final List<Order> orders = orderRepositoryCustom.search(filter);
        assertFalse(orders.isEmpty());
        assertTrue(orders.contains(order));
    }

    @Test
    void shouldSearchOrdersWithMaxPriceFilters() {
        final Order order = JSONUtil.fromFile("samples/order.json", Order.class);
        when(template.find(any(), eq(Order.class))).thenReturn(Collections.singletonList(order));
        final OrderFilterDTO filter = OrderFilterDTO.builder()
                .email("teste@teste.com")
                .status("WAIT_PAYMENT")
                .maxPrice(BigDecimal.valueOf(5000.0))
                .build();

        final List<Order> orders = orderRepositoryCustom.search(filter);
        assertFalse(orders.isEmpty());
        assertTrue(orders.contains(order));
    }

    @Test
    void shouldSearchOrdersWithoutFilters() {
        when(template.find(any(), eq(Order.class))).thenReturn(Collections.emptyList());
        final OrderFilterDTO filter = OrderFilterDTO.builder()
                .email("teste@teste.com")
                .status("WAIT_PAYMENT")
                .maxPrice(BigDecimal.valueOf(5000.0))
                .build();

        final List<Order> orders = orderRepositoryCustom.search(filter);
        assertTrue(orders.isEmpty());
    }

}