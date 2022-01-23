package br.com.estore.orderquery.repositories;

import br.com.estore.orderquery.domain.dtos.OrderFilterDTO;
import br.com.estore.orderquery.domain.entities.Order;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> search(final OrderFilterDTO filter);

}
