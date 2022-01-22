package br.com.estore.productquery.exceptions;

public class RabbitMqException extends RuntimeException {

    public RabbitMqException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
