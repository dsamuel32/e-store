package br.com.estore.ordercommand.components;

import org.springframework.scheduling.annotation.Async;

public interface Publisher<T> {

    @Async
    void publish(final T body);

}
