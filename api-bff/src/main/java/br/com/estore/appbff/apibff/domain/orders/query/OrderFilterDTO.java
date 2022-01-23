package br.com.estore.appbff.apibff.domain.orders.query;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderFilterDTO {

    private String email;
    private String status;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    @Builder.Default
    private Integer pageNumber = 1;
    @Builder.Default
    private Integer itemsPerPage = 10;

}
