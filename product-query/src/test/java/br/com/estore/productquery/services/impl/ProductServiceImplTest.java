package br.com.estore.productquery.services.impl;

import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.domain.entities.Product;
import br.com.estore.productquery.repositories.ProductCustomRepository;
import br.com.estore.productquery.repositories.ProductRepository;
import br.com.estore.productquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductServiceImpl")
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductCustomRepository productCustomRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldSearchProducts() {
        final Product product = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);
        final ProductDTO productDTO = JSONUtil.fromFile("samples/productDTOSave.json", ProductDTO.class);
        final ProductDTO expected = JSONUtil.fromFile("samples/productDTOSave.json", ProductDTO.class);
        when(productCustomRepository.search(any(ProductFilterDTO.class))).thenReturn(Collections.singletonList(product));
        when(mapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productDTO);
        final ProductFilterDTO filters = ProductFilterDTO.builder()
                .category("eletronicos")
                .description("tv")
                .name("tv")
                .itemsPerPage(10)
                .pageNumber(1)
                .build();

        final List<ProductDTO> products = service.search(filters);

        assertTrue(products.contains(expected));

    }

    @Test
    void shouldNotSearchProducts() {
        when(productCustomRepository.search(any(ProductFilterDTO.class))).thenReturn(Collections.emptyList());
        final ProductFilterDTO filters = ProductFilterDTO.builder()
                .itemsPerPage(10)
                .pageNumber(1)
                .build();

        final List<ProductDTO> products = service.search(filters);

        assertTrue(products.isEmpty());
        verifyNoInteractions(mapper);

    }

    @Test
    void shouldProcessActiveProduct() {
        final ProductDTO product = JSONUtil.fromFile("samples/productDTOSave.json", ProductDTO.class);
        final Product productEntity = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);

        when(mapper.map(any(ProductDTO.class), eq(Product.class))).thenReturn(productEntity);

        service.process(product);

        verify(productRepository).save(productEntity);
        verify(mapper).map(product, Product.class);

    }

    @Test
    void shouldProcessNonActiveProduct() {
        final ProductDTO product = JSONUtil.fromFile("samples/productDTOToDelete.json", ProductDTO.class);

        service.process(product);

        verify(productRepository).deleteById(product.getId());
        verify(productRepository, never()).save(any(Product.class));
        verifyNoInteractions(mapper);

    }

}