package br.com.estore.ordercommand.services;

import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.dtos.PaymentDTO;

public interface OrderService {

    OrderDTO create(final OrderDTO order);

    OrderDTO confirm(final Long orderId, final PaymentDTO payment);

}
