package br.com.estore.appbff.apibff.components.proxies;

import br.com.estore.appbff.apibff.components.clients.OrderQueryClient;
import br.com.estore.appbff.apibff.domain.orders.query.OrderQueryDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderFilterDTO;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderQueryProxy {

    private final OrderQueryClient client;

    public OrderQueryProxy(OrderQueryClient client) {
        this.client = client;
    }

    public List<OrderQueryDTO> search(final OrderFilterDTO filter) {
        try {
            return client.search(
                    filter.getEmail(),
                    filter.getStatus(),
                    filter.getMinPrice(),
                    filter.getMaxPrice(),
                    filter.getItemsPerPage(),
                    filter.getPageNumber()
            );
        } catch (FeignException e) {
            throw e;
        }
    }
}
