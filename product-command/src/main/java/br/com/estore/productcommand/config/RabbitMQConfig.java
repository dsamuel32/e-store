package br.com.estore.productcommand.config;

import br.com.estore.productcommand.constants.RabbitMQ;
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
    public Queue productQueue() {
        return QueueBuilder.durable(RabbitMQ.PRODUCT_QUEUE).build();
    }

    @Bean
    public Declarables bindings() {
        return new Declarables(
                productQueue(),
                BindingBuilder.bind(productQueue()).to(exchange()).with(RabbitMQ.PRODUCT_ROUTING_KEY)
        );
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
