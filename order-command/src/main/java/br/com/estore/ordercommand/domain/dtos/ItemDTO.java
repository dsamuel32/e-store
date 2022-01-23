package br.com.estore.ordercommand.domain.dtos;

import br.com.estore.ordercommand.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ItemDTO {

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    private Long id;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private Long code;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    private BigDecimal price;

}
