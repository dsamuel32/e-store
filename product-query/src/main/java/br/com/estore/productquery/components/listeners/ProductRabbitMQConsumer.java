package br.com.estore.productquery.components.listeners;

import br.com.estore.productquery.constants.RabbitMQ;
import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.exceptions.RabbitMqException;
import br.com.estore.productquery.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductRabbitMQConsumer {

    private final ObjectMapper mapper;
    private final ProductService service;

    public ProductRabbitMQConsumer(final ObjectMapper mapper,
                                   final ProductService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @RabbitListener(queues = RabbitMQ.PRODUCT_QUEUE)
    public void listener(final String body) {

        try {
            final ProductDTO product = mapper.readValue(body, ProductDTO.class);
            log.info("ProductRabbitMQConsumer.listener - Product id: {}", product.getId());
            service.process(product);
        } catch (Exception e) {
            log.error("ProductRabbitMQConsumer.listener - Exception", e);
            throw new RabbitMqException(e.getMessage(), e);
        }

    }
}
