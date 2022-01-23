package br.com.estore.appbff.apibff.domain.orders.command;

import br.com.estore.appbff.apibff.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDTO {

    private Long id;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String street;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String number;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String additionalInfo;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String postalCode;

}
