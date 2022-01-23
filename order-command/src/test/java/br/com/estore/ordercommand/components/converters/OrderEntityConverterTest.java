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

@DisplayName("Tests for OrderEntityConverter")
@ExtendWith(SpringExtension.class)
class OrderEntityConverterTest {

    @InjectMocks
    private OrderEntityConverter converter;

    @Test
    void shouldConvertOrderDTOToEntity() {
        final OrderDTO order = JSONUtil.fromFile("samples/orderDTOToSave.json", OrderDTO.class);
        final Order expected = JSONUtil.fromFile("samples/orderEntityToSave.json", Order.class);

        final Order result = converter.convert(order);

        assertEquals(expected, result);
    }

}