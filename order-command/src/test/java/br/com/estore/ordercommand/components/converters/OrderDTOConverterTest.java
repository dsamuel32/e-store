package br.com.estore.ordercommand.components.converters;

import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.entities.Order;
import br.com.estore.ordercommand.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for OrderDTOConverter")
@ExtendWith(SpringExtension.class)
class OrderDTOConverterTest {

    @InjectMocks
    private OrderDTOConverter converter;

    @Test
    void shouldConvertOrderDTOToEntity() {
        final Order order = JSONUtil.fromFile("samples/orderEntityToSave.json", Order.class);
        final OrderDTO expected = JSONUtil.fromFile("samples/orderDTOToSave.json", OrderDTO.class);

        final OrderDTO result = converter.convert(order);

        assertEquals(expected, result);
    }

}