package br.com.estore.orderquery.domain.dtos;

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
