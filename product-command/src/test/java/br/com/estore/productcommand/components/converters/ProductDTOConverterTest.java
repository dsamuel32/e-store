package br.com.estore.productcommand.components.converters;

import br.com.estore.productcommand.components.converters.ProductDTOConverter;
import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Product;
import br.com.estore.productcommand.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for ProductDTOConverter")
@ExtendWith(SpringExtension.class)
class ProductDTOConverterTest {

    @InjectMocks
    private ProductDTOConverter converter;

    @Test
    void shouldConvertProductEntityToDTO(){
        final ProductDTO productDTO = JSONUtil.fromFile("samples/save/productDTOSave.json", ProductDTO.class);
        final Product productEntity = JSONUtil.fromFile("samples/save/productEntitySave.json", Product.class);

        final var result = converter.convert(productEntity);

        assertEquals(productDTO, result);
    }

}