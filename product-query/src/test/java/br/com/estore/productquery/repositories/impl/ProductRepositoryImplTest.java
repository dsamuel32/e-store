package br.com.estore.productquery.repositories.impl;

import br.com.estore.productquery.domain.dtos.ProductFilterDTO;
import br.com.estore.productquery.domain.entities.Product;
import br.com.estore.productquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductRepositoryImpl")
@ExtendWith(SpringExtension.class)
class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    void shouldSearchProducts() {
        final Product product = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);
        final Product expected = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);
        when(mongoTemplate.find(any(), eq(Product.class))).thenReturn(Collections.singletonList(product));

        final ProductFilterDTO filters = ProductFilterDTO.builder()
                .category("eletronicos")
                .description("tv")
                .name("tv")
                .itemsPerPage(10)
                .pageNumber(1)
                .build();

        final List<Product> products = productRepository.search(filters);

        assertTrue(products.contains(expected));

    }

    @Test
    void shouldReturnEmptyListWhenSearchProducts() {
        when(mongoTemplate.find(any(), eq(Product.class))).thenReturn(Collections.emptyList());

        final ProductFilterDTO filters = ProductFilterDTO.builder()
                .itemsPerPage(10)
                .pageNumber(1)
                .build();

        final List<Product> products = productRepository.search(filters);

        assertTrue(products.isEmpty());
    }

}