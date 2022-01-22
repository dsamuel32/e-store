package br.com.estore.productquery.domain.dtos;

import br.com.estore.productquery.constants.ApplicationConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ImageDTO {

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String link;

    @NotBlank(message = ApplicationConstants.REQUIRED_FIELD)
    private String description;

    @NotNull(message = ApplicationConstants.REQUIRED_FIELD)
    private Boolean main = false;
}
