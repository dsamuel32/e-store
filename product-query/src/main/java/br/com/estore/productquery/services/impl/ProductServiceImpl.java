package br.com.estore.productquery.services.impl;

import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.domain.entities.Product;
import br.com.estore.productquery.repositories.ProductCustomRepository;
import br.com.estore.productquery.repositories.ProductRepository;
import br.com.estore.productquery.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductCustomRepository customRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(final ProductCustomRepository customRepository,
                              final ProductRepository productRepository,
                              final ModelMapper mapper) {
        this.customRepository = customRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDTO> search(final ProductFilterDTO filters) {
        return customRepository.search(filters).stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void process(final ProductDTO product) {
        Optional.of(product)
                .filter(ProductDTO::getActive)
                .map(p -> mapper.map(p, Product.class))
                .ifPresentOrElse(productRepository::save, () -> productRepository.deleteById(product.getId()));
    }

}
