package br.com.estore.productcommand.components.converters;

import br.com.estore.productcommand.domain.dtos.CategoryDTO;
import br.com.estore.productcommand.domain.dtos.ImageDTO;
import br.com.estore.productcommand.domain.dtos.ProductDTO;
import br.com.estore.productcommand.domain.entities.Category;
import br.com.estore.productcommand.domain.entities.Image;
import br.com.estore.productcommand.domain.entities.Product;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTOConverter extends AbstractConverter<Product, ProductDTO> {

    @Override
    protected ProductDTO convert(final Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .category(buildCategory(product.getCategory()))
                .description(product.getDescription())
                .images(buildImages(product.getImages()))
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    private CategoryDTO buildCategory(final Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
    }

    private List<ImageDTO> buildImages(final List<Image> images) {
        return images.stream()
                .map(this::buildImage)
                .collect(Collectors.toList());
    }

    private ImageDTO buildImage(final Image image) {
        return ImageDTO.builder()
                .link(image.getLink())
                .main(image.getMain())
                .description(image.getDescription())
                .build();
    }
}
