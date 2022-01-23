package br.com.estore.orderquery.components.converters;

import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.domain.entities.Order;
import br.com.estore.orderquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for OrderDTOConverter")
@ExtendWith(SpringExtension.class)
class OrderDTOConverterTest {

    @InjectMocks
    private OrderDTOConverter converter;

    @Test
    void shouldConvertOrderDTOToEntity() {
        final Order order = JSONUtil.fromFile("samples/order.json", Order.class);
        final OrderDTO expected = JSONUtil.fromFile("samples/orderDTO.json", OrderDTO.class);

        final OrderDTO result = converter.convert(order);

        assertEquals(expected, result);
    }

}