package br.com.estore.orderquery.domain.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ItemDTO {

    private Long id;

    private Long code;

    private String name;

    private BigDecimal price;

}
