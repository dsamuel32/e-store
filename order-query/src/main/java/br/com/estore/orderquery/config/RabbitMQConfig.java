package br.com.estore.orderquery.config;

import br.com.estore.orderquery.constants.RabbitMQ;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(RabbitMQ.DIRECT_EXCHANGE)
                .durable(Boolean.TRUE)
                .build();
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(RabbitMQ.ORDER_QUEUE).build();
    }

    @Bean
    public Declarables bindings() {
        return new Declarables(
                orderQueue(),
                BindingBuilder.bind(orderQueue()).to(exchange()).with(RabbitMQ.ORDER_ROUTING_KEY)
        );
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
