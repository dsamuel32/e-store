package br.com.estore.appbff.apibff.services;

import br.com.estore.appbff.apibff.domain.orders.command.OrderCommandDTO;
import br.com.estore.appbff.apibff.domain.orders.command.PaymentDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderFilterDTO;
import br.com.estore.appbff.apibff.domain.orders.query.OrderQueryDTO;

import java.util.List;

public interface OrderService {

    List<OrderQueryDTO> search(final OrderFilterDTO filter);

    OrderCommandDTO create(final OrderCommandDTO order);

    OrderCommandDTO confirm(final Long id, final PaymentDTO payment);

}
