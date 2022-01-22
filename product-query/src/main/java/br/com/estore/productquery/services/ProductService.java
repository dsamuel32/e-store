package br.com.estore.productquery.services;

import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.dtos.ProductFilterDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> search(final ProductFilterDTO filters);

    void process(final ProductDTO product);

}
