package br.com.estore.appbff.apibff.domain.products;

import lombok.*;

import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponseDTO {

    private String code;
    private String message;
    private Set<String> details;

}
