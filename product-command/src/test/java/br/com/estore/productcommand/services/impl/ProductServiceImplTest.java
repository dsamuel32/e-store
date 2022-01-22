package br.com.estore.productcommand.services.impl;

import br.com.estore.productcommand.components.publishers.ProductRabbitMQPublisher;
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

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Mock
    private ProductRabbitMQPublisher publisher;

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
        verify(publisher).publish(result);

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
        verify(publisher).publish(result);
    }

    @Test
    void shouldDisableProduct() {
        final Product productEntityToDisable = JSONUtil.fromFile("samples/disable/productEntityToDisable.json", Product.class);
        final Product productEntityDisabled = JSONUtil.fromFile("samples/disable/productEntityDisable.json", Product.class);
        final ProductDTO productToUpdated = JSONUtil.fromFile("samples/update/productDTOToUpdate.json", ProductDTO.class);
        when(productRepository.findByIdAndActive(anyLong(), anyBoolean())).thenReturn(Optional.of(productEntityToDisable));
        when(productRepository.save(any(Product.class))).thenReturn(productEntityDisabled);
        when(mapper.map(any(Product.class), eq(ProductDTO.class))).thenReturn(productToUpdated);
        service.disableProductById(1l);
        verify(productRepository).findByIdAndActive(1l, true);
        verify(productRepository).save(productEntityDisabled);
    }

    @Test
    void shouldTryDisableProductButProductNotFound() {
        when(productRepository.findByIdAndActive(anyLong(), anyBoolean())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.disableProductById(1l));
    }

}