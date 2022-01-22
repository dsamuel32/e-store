package br.com.estore.productquery.components.converters;

import br.com.estore.productquery.utils.JSONUtil;
import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for ProductEntityConverter")
@ExtendWith(SpringExtension.class)
class ProductEntityConverterTest {

    @InjectMocks
    private ProductEntityConverter converter;

    @Test
    void shouldConvertProductDTOToEntity() {
        final Product productEntity = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);
        final ProductDTO productDTO = JSONUtil.fromFile("samples/productDTOSave.json", ProductDTO.class);

        final Product result = converter.convert(productDTO);

        assertEquals(productEntity, result);
    }

}