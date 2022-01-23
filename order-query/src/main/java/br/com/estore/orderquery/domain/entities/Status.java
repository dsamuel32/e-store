package br.com.estore.orderquery.domain.entities;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Status {

    private String code;

    private String description;

}
