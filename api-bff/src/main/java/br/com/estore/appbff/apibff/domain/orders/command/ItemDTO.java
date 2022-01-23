package br.com.estore.appbff.apibff.domain.orders.command;

import br.com.estore.appbff.apibff.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.Min;
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

    private Long id;

    @NotNull(message = ApplicationConstants.REQUIRED_FIELD)
    @Min(message = ApplicationConstants.INVALID_VALUE, value = 1)
    private Long code;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Min(message = ApplicationConstants.INVALID_VALUE, value = 1)
    private BigDecimal price;

}
