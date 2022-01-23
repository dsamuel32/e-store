package br.com.estore.appbff.apibff.services.impl;

import br.com.estore.appbff.apibff.components.proxies.OrderCommandProxy;
import br.com.estore.appbff.apibff.components.proxies.OrderQueryProxy;
import br.com.estore.appbff.apibff.domain.orders.command.OrderCommandDTO;
import br.com.estore.appbff.apibff.domain.orders.command.PaymentDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderQueryDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderFilterDTO;
import br.com.estore.appbff.apibff.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderQueryProxy queryProxy;
    private final OrderCommandProxy commandProxy;

    public OrderServiceImpl(final OrderQueryProxy queryProxy,
                            final OrderCommandProxy commandProxy) {
        this.queryProxy = queryProxy;
        this.commandProxy = commandProxy;
    }

    @Override
    public List<OrderQueryDTO> search(OrderFilterDTO filter) {
        return queryProxy.search(filter);
    }

    @Override
    public OrderCommandDTO create(final OrderCommandDTO order) {
        return commandProxy.create(order);
    }

    @Override
    public OrderCommandDTO confirm(final Long id, final PaymentDTO payment) {
        return commandProxy.confirm(id, payment);
    }

}
