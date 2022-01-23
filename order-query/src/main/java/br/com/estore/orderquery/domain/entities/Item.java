package br.com.estore.orderquery.domain.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Item {

    private Long id;

    private Long code;

    private String name;

    private BigDecimal price;

}
