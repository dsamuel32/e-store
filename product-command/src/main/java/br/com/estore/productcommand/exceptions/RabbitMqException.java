package br.com.estore.productcommand.exceptions;

public class RabbitMqException extends RuntimeException {

    public RabbitMqException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
