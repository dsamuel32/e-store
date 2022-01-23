package br.com.estore.appbff.apibff.components.proxies;

import br.com.estore.appbff.apibff.components.clients.OrderCommandClient;
import br.com.estore.appbff.apibff.domain.orders.command.OrderCommandDTO;
import br.com.estore.appbff.apibff.domain.orders.command.PaymentDTO;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandProxy {

    private final OrderCommandClient client;


    public OrderCommandProxy(final OrderCommandClient client) {
        this.client = client;
    }

    public OrderCommandDTO create(final OrderCommandDTO order) {
        try {
            return client.create(order);
        } catch (FeignException e) {
            throw e;
        }
    }

    public OrderCommandDTO confirm(final Long id, final PaymentDTO payment) {
        try {
            return client.confirm(id, payment);
        } catch (FeignException e) {
            throw e;
        }
    }

}
