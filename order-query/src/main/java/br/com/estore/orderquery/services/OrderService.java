package br.com.estore.orderquery.services;

import br.com.estore.orderquery.domain.dtos.OrderDTO;
import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;

import java.util.List;

public interface OrderService {

    void process(final OrderDTO order);

    List<OrderDTO> search(final OrderFilterDTO filter);

}
