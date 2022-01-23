package br.com.estore.ordercommand.domain.dtos;

import br.com.estore.ordercommand.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PaymentDTO {

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Min(message = ApplicationConstants.INVALID_VALUE, value = 1)
    private Integer creditCartNumber;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String expirationDate;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Min(message = ApplicationConstants.INVALID_VALUE, value = 3)
    private Integer verificationCode;

}