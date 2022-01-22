package br.com.estore.productcommand.converters;

import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Product;
import br.com.estore.productcommand.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for ProductEntityConverter")
@ExtendWith(SpringExtension.class)
class ProductEntityConverterTest {

    @InjectMocks
    private ProductEntityConverter converter;

    @Test
    void shouldConvertProductDTOToEntity() {
        final Product productEntity = JSONUtil.fromFile("samples/save/productEntitySave.json", Product.class);
        final ProductDTO productDTO = JSONUtil.fromFile("samples/save/productDTOSave.json", ProductDTO.class);

        final Product result = converter.convert(productDTO);

        assertEquals(productEntity, result);
    }

}