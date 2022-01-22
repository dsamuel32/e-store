package br.com.estore.productcommand.components.publishers;

import br.com.estore.productcommand.constants.RabbitMQ;
import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.exceptions.RabbitMqException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductRabbitMQPublisher implements Publisher<ProductDTO>{

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public ProductRabbitMQPublisher(final RabbitTemplate rabbitTemplate, final ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void publish(final ProductDTO body) {

        try {
            log.info("ProductPublisher.publish - Product id: {}", body.getId());
            final String json = mapper.writeValueAsString(body);
            rabbitTemplate.convertAndSend(RabbitMQ.DIRECT_EXCHANGE, RabbitMQ.PRODUCT_ROUTING_KEY, json);
        } catch (Exception e) {
            log.error("ProductPublisher.publish - JsonProcessingException", e);
            throw new RabbitMqException(e.getMessage(), e);
        }

    }
}
