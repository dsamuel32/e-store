package br.com.estore.ordercommand.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    void shouldPublishProductInQueue() throws JsonProcessingException {
        /*final ProductDTO product = JSONUtil.fromFile("samples/save/productDTOSave.json", ProductDTO.class);
        final String body = JSONUtil.fromString("samples/save/productDTOSave.json");
        when(mapper.writeValueAsString(any())).thenReturn(body);

        publisher.publish(product);
        verify(template).convertAndSend(RabbitMQ.DIRECT_EXCHANGE, RabbitMQ.PRODUCT_ROUTING_KEY, body);*/
    }

    @Test
    void shouldTryPublishProductInQueue() throws JsonProcessingException {
        /*final ProductDTO product = JSONUtil.fromFile("samples/save/productDTOSave.json", ProductDTO.class);
        when(mapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
        assertThrows(RabbitMqException.class, () -> publisher.publish(product));*/
    }

}