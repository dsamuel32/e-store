package br.com.estore.orderquery.components.listerners;

import br.com.estore.orderquery.constants.RabbitMQ;
import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.exceptions.RabbitMqException;
import br.com.estore.orderquery.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRabbitMQConsumer {

    private final ObjectMapper mapper;
    private final OrderService service;

    public OrderRabbitMQConsumer(final ObjectMapper mapper, final OrderService service) {
        this.mapper = mapper;
        this.service = service;
    }


    @RabbitListener(queues = RabbitMQ.ORDER_QUEUE)
    public void listener(final String body) {

        try {
            final OrderDTO order = mapper.readValue(body, OrderDTO.class);
            log.info("OrderRabbitMQConsumer.listener - Order id: {}", order.getId());
            service.process(order);
        } catch (Exception e) {
            log.error("OrderRabbitMQConsumer.listener - Exception", e);
            throw new RabbitMqException(e.getMessage(), e);
        }

    }

}
