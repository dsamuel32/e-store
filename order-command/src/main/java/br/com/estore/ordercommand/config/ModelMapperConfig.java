package br.com.estore.ordercommand.config;

import br.com.estore.ordercommand.components.converters.OrderDTOConverter;
import br.com.estore.ordercommand.components.converters.OrderEntityConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private final OrderEntityConverter orderEntityConverter;
    private final OrderDTOConverter orderDTOConverter;

    public ModelMapperConfig(final OrderEntityConverter orderEntityConverter,
                             final OrderDTOConverter orderDTOConverter) {
        this.orderEntityConverter = orderEntityConverter;
        this.orderDTOConverter = orderDTOConverter;
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
        modelMapper.addConverter(orderEntityConverter);
        modelMapper.addConverter(orderDTOConverter);
        return modelMapper;
    }

}
