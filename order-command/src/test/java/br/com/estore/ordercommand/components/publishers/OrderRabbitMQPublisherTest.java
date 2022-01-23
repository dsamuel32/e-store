package br.com.estore.ordercommand.components.publishers;

import br.com.estore.ordercommand.components.publishers.OrderRabbitMQPublisher;
import br.com.estore.ordercommand.constants.RabbitMQ;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.exceptions.RabbitMqException;
import br.com.estore.ordercommand.utils.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Tests for OrderRabbitMQPublisher")
@ExtendWith(SpringExtension.class)
class OrderRabbitMQPublisherTest {

    @InjectMocks
    private OrderRabbitMQPublisher publisher;

    @Mock
    private RabbitTemplate template;

    @Mock
    private ObjectMapper mapper;

    @Test
    void shouldPublishOrderInQueue() throws JsonProcessingException {
        final OrderDTO order = JSONUtil.fromFile("samples/orderDTOSaved.json", OrderDTO.class);
        final String body = JSONUtil.fromString("samples/orderDTOSaved.json");
        when(mapper.writeValueAsString(any())).thenReturn(body);

        publisher.publish(order);
        verify(template).convertAndSend(RabbitMQ.DIRECT_EXCHANGE, RabbitMQ.ORDER_ROUTING_KEY, body);
    }

    @Test
    void shouldTryPublishOrderInQueue() throws JsonProcessingException {
        final OrderDTO order = JSONUtil.fromFile("samples/orderDTOSaved.json", OrderDTO.class);
        when(mapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
        assertThrows(RabbitMqException.class, () -> publisher.publish(order));
    }

}