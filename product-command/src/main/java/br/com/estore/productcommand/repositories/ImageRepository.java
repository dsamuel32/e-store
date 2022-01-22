package br.com.estore.productcommand.repositories;

import br.com.estore.productcommand.domain.entities.Image;
import br.com.estore.productcommand.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Long deleteByProduct(final Product product);

}
