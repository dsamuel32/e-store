package br.com.estore.productcommand.services.impl;

import br.com.estore.productcommand.components.publishers.Publisher;
import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Product;
import br.com.estore.productcommand.repositories.ProductRepository;
import br.com.estore.productcommand.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final Publisher<ProductDTO> publisher;

    public ProductServiceImpl(final ProductRepository productRepository,
                              final ModelMapper mapper,
                              final Publisher<ProductDTO> publisher) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO save(final ProductDTO product) {
        final Product productToSave = convertProductDTOToEntity(product);
        final ProductDTO productSaved = saveOrUpdate(productToSave);
        return productSaved;
    }

    private Product convertProductDTOToEntity(final ProductDTO product) {
        return mapper.map(product, Product.class);
    }

    private ProductDTO saveOrUpdate(final Product product) {
        final Product productSave = productRepository.save(product);
        return publishAndUpdate(productSave);
    }

    private ProductDTO publishAndUpdate(final Product product) {
        final ProductDTO productSaved = mapper.map(product, ProductDTO.class);
        notify(productSaved);
        markProductPublish(product);
        return productSaved;
    }

    private void markProductPublish(final Product product) {
        final Product productPublish = product.toBuilder()
                .published(true)
                .build();
        productRepository.save(productPublish);
    }

    @Override
    public ProductDTO update(final ProductDTO product) {
        final Product productToUpdate = convertProductDTOToEntity(product);
        return saveOrUpdate(productToUpdate);
    }

    @Override
    public void disableProductById(final Long id) {
        productRepository.findByIdAndActive(id, true)
                .map(p -> p.toBuilder().active(false).published(false).build())
                .map(this::saveOrUpdate)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product ID: %s not found", id)));
    }

    @Override
    public void republishProducts() {
        productRepository.findByPublished(false)
                .forEach(this::publishAndUpdate);
    }

    private void notify(final ProductDTO product) {
        publisher.publish(product);
    }

}
