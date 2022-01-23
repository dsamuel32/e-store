package br.com.estore.ordercommand.domain.dtos;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StatusDTO {

    private String code;

    private String description;

}
