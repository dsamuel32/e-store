package br.com.estore.productquery.domain.entities;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Category {

    private Long id;

    private String description;

}
