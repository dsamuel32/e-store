package br.com.estore.appbff.apibff.domain.products;

import br.com.estore.appbff.apibff.constants.ApplicationConstants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ProductDTO {

    private Long id;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String description;

    @NotNull(message = ApplicationConstants.REQUIRED_FIELD)
    private BigDecimal price;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String brand;

    @NotNull(message = ApplicationConstants.REQUIRED_FIELD)
    @Valid
    private CategoryDTO category;

    @Valid
    @NotEmpty(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    private List<ImageDTO> images;

    @Builder.Default
    private Boolean active = true;

}
