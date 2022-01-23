package br.com.estore.appbff.apibff.domain.orders.command;

import br.com.estore.appbff.apibff.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CustomerDTO {

    private Long id;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String documentNumber;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    @Email(message = ApplicationConstants.INVALID_EMAIL)
    private String email;

}
