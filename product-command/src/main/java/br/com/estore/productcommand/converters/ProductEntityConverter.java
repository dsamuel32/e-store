package br.com.estore.productcommand.converters;

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
public class ProductEntityConverter extends AbstractConverter<ProductDTO, Product> {

    @Override
    protected Product convert(final ProductDTO product) {
        return Product.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .category(buildCategory(product.getCategory()))
                .description(product.getDescription())
                .published(false)
                .active(true)
                .images(buildImages(product.getImages()))
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    private Category buildCategory(final CategoryDTO category) {
        return Category.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
    }

    private List<Image> buildImages(final List<ImageDTO> images) {
        return images.stream()
                .map(this::buildImage)
                .collect(Collectors.toList());
    }

    private Image buildImage(final ImageDTO image) {
        return Image.builder()
                .link(image.getLink())
                .main(image.getMain())
                .description(image.getDescription())
                .build();
    }
}
