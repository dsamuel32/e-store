package br.com.estore.productcommand.repositories;

import br.com.estore.productcommand.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndActive(final Long id, final Boolean active);

    List<Product> findByPublished(final Boolean published);

}
