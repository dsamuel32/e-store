package br.com.estore.ordercommand.services;

import br.com.estore.ordercommand.domain.dtos.OrderDTO;

public interface OrderService {

    OrderDTO create(final OrderDTO order);

}
