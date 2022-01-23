package br.com.estore.ordercommand.domain.dtos;

import br.com.estore.ordercommand.constants.ApplicationConstants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CostumerDTO {

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Valid
    private Long id;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String name;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String documentNumber;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String email;

}
