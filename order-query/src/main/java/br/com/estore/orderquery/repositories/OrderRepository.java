package br.com.estore.orderquery.repositories;

import br.com.estore.orderquery.domain.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
