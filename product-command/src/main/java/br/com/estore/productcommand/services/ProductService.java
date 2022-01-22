package br.com.estore.productcommand.services;

import br.com.estore.productcommand.domain.dtos.ProductDTO;

public interface ProductService {

    ProductDTO save(final ProductDTO product);

    ProductDTO update(final ProductDTO productDTO);

    void deleteById(final Long id);

}
