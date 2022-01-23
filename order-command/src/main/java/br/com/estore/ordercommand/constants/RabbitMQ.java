package br.com.estore.ordercommand.constants;

public final class RabbitMQ {

    private static final String PREFIX = "estore.";

    public static final String DIRECT_EXCHANGE = PREFIX + "direct.exchange";

    public static final String ORDER_ROUTING_KEY = ".received";

    public static final String ORDER_QUEUE = PREFIX + "order" + ORDER_ROUTING_KEY;

}
