package br.com.estore.productcommand.constants;

public final class RabbitMQ {

    private static final String PREFIX = "estore.";

    public static final String DIRECT_EXCHANGE = PREFIX + "direct.exchange";

    public static final String PRODUCT_ROUTING_KEY = "product.received";

    public static final String PRODUCT_QUEUE = PREFIX + PRODUCT_ROUTING_KEY;

}
