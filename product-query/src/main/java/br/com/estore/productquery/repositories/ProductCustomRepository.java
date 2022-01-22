package br.com.estore.productquery.repositories;

import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.domain.entities.Product;

import java.util.List;

public interface ProductCustomRepository {

    List<Product> search(final ProductFilterDTO filters);

}
