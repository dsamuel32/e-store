package br.com.estore.orderquery.constants;

public final class RabbitMQ {

    private static final String PREFIX = "estore.";

    public static final String DIRECT_EXCHANGE = PREFIX + "direct.exchange";

    public static final String ORDER_ROUTING_KEY = "order.received";

    public static final String ORDER_QUEUE = PREFIX + ORDER_ROUTING_KEY;

}
