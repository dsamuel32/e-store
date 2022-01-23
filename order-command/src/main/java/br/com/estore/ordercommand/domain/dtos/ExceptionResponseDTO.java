package br.com.estore.ordercommand.domain.dtos;

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
