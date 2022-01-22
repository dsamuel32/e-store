package br.com.estore.productquery.components.converters;

import br.com.estore.productquery.domain.dtos.ProductDTO;
import br.com.estore.productquery.domain.entities.Product;
import br.com.estore.productquery.utils.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for ProductDTOConverter")
@ExtendWith(SpringExtension.class)
class ProductDTOConverterTest {

    @InjectMocks
    private ProductDTOConverter converter;

    @Test
    void shouldConvertProductEntityToDTO(){
        final ProductDTO productDTO = JSONUtil.fromFile("samples/productDTOToSave.json", ProductDTO.class);
        final Product productEntity = JSONUtil.fromFile("samples/productEntitySave.json", Product.class);

        final var result = converter.convert(productEntity);

        assertEquals(productDTO, result);
    }

}