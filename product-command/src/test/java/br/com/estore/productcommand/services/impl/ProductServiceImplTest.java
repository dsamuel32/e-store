package br.com.estore.productcommand.services.impl;

import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Product;
import br.com.estore.productcommand.repositories.ProductRepository;
import br.com.estore.productcommand.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Tests for ProductServiceImpl")
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldSaveProduct() {
        final Product productEntitySaved = JSONUtil.fromFile("samples/save/productEntitySave.json", Product.class);
        final Product productEntityToSave = JSONUtil.fromFile("samples/save/productEntityToSave.json", Product.class);
        final ProductDTO productToSaved = JSONUtil.fromFile("samples/save/productDTOToSave.json", ProductDTO.class);
        final ProductDTO productSaved = JSONUtil.fromFile("samples/save/productDTOSave.json", ProductDTO.class);

        when(productRepository.save(any(Product.class))).thenReturn(productEntitySaved);
        when(mapper.map(any(ProductDTO.class), eq(Product.class))).thenReturn(productEntityToSave);
        when(mapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productSaved);

        final ProductDTO result = service.save(productToSaved);

        assertEquals(productSaved, result);
        verify(productRepository).save(productEntityToSave);

    }

    @Test
    void shouldUpdateProduct() {
        final Product productEntityUpdated = JSONUtil.fromFile("samples/update/productEntityUpdate.json", Product.class);
        final Product productEntityToUpdate = JSONUtil.fromFile("samples/update/productEntityToUpdate.json", Product.class);
        final ProductDTO productToUpdated = JSONUtil.fromFile("samples/update/productDTOToUpdate.json", ProductDTO.class);
        final ProductDTO productUpdated = JSONUtil.fromFile("samples/update/productDTOUpdate.json", ProductDTO.class);

        when(productRepository.save(any(Product.class))).thenReturn(productEntityUpdated);
        when(mapper.map(any(ProductDTO.class), eq(Product.class))).thenReturn(productEntityToUpdate);
        when(mapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productUpdated);

        final ProductDTO result = service.update(productToUpdated);

        assertEquals(productUpdated, result);
        verify(productRepository).save(productEntityToUpdate);
    }

    @Test
    void shouldDeleteProduct() {
        service.deleteById(1l);;
        verify(productRepository).deleteById(1l);
    }

}