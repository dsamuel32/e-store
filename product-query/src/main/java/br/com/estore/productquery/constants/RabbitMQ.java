package br.com.estore.productquery.constants;

public final class RabbitMQ {

    private static final String PREFIX = "estore.";

    public static final String DIRECT_EXCHANGE = PREFIX + "direct.exchange";

    public static final String PRODUCT_ROUTING_KEY = ".received";

    public static final String PRODUCT_QUEUE = PREFIX + "product" + PRODUCT_ROUTING_KEY;

}
