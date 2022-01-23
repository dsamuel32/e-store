package br.com.estore.ordercommand.domain.dtos;

import br.com.estore.ordercommand.constants.ApplicationConstants;
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
