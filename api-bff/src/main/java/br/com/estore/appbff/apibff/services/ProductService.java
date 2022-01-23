package br.com.estore.appbff.apibff.services;

import br.com.estore.appbff.apibff.domain.products.ProductDTO;
import br.com.estore.appbff.apibff.domain.products.ProductFilterDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> search(final ProductFilterDTO filters);

    ProductDTO save(final ProductDTO product);

    ProductDTO update(final ProductDTO product);

    void delete(final Long id);

}
