package br.com.estore.appbff.apibff.domain.orders.query;

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
