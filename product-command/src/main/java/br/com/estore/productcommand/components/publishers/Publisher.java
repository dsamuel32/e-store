package br.com.estore.productcommand.components.publishers;

import org.springframework.scheduling.annotation.Async;

public interface Publisher<T> {

    @Async
    void publish(final T body);

}
