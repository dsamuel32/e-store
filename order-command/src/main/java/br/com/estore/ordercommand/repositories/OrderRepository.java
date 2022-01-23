package br.com.estore.ordercommand.repositories;

import br.com.estore.ordercommand.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndStatusCode(final Long orderId, final String statusCode);

    List<Order> findByPublished(final Boolean published);
}
