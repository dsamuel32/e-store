package br.com.estore.productcommand.config;

import br.com.estore.productcommand.converters.ProductDTOConverter;
import br.com.estore.productcommand.converters.ProductEntityConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private final ProductEntityConverter productEntityConverter;
    private final ProductDTOConverter productDTOConverter;

    public ModelMapperConfig(final ProductEntityConverter productEntityConverter,
                             final ProductDTOConverter productDTOConverter) {
        this.productEntityConverter = productEntityConverter;
        this.productDTOConverter = productDTOConverter;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
        modelMapper.addConverter(productEntityConverter);
        modelMapper.addConverter(productDTOConverter);
        return modelMapper;
    }

}
