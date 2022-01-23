package br.com.estore.ordercommand.components.publishers;

import br.com.estore.ordercommand.constants.RabbitMQ;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.exceptions.RabbitMqException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRabbitMQPublisher implements Publisher<OrderDTO> {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public OrderRabbitMQPublisher(final RabbitTemplate rabbitTemplate, final ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void publish(final OrderDTO body) {

        try {
            log.info("ProductPublisher.publish - Product id: {}", body.getId());
            final String json = mapper.writeValueAsString(body);
            rabbitTemplate.convertAndSend(RabbitMQ.DIRECT_EXCHANGE, RabbitMQ.ORDER_ROUTING_KEY, json);
        } catch (Exception e) {
            log.error("ProductPublisher.publish - JsonProcessingException", e);
            throw new RabbitMqException(e.getMessage(), e);
        }

    }
}
