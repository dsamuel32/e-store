package br.com.estore.productcommand.services.impl;

import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Product;
import br.com.estore.productcommand.repositories.ProductRepository;
import br.com.estore.productcommand.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(final ProductRepository productRepository,
                              final ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO save(final ProductDTO product) {
        final Product productToSave = convertProductDTOToEntity(product);
        return saveOrUpdate(productToSave);
    }

    private Product convertProductDTOToEntity(final ProductDTO product) {
        return mapper.map(product, Product.class);
    }

    private ProductDTO saveOrUpdate(final Product product) {
        final Product productSave = productRepository.save(product);
        return mapper.map(productSave, ProductDTO.class);
    }

    @Override
    public ProductDTO update(final ProductDTO product) {
        final Product productToUpdate = convertProductDTOToEntity(product);
        return saveOrUpdate(productToUpdate);
    }

    @Override
    public void deleteById(final Long id) {
        productRepository.deleteById(id);
    }

}
