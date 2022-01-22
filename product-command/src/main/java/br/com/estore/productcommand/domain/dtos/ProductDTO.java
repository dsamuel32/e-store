package br.com.estore.productcommand.domain.dtos;

import br.com.estore.productcommand.constants.ApplicationConstants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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

}
