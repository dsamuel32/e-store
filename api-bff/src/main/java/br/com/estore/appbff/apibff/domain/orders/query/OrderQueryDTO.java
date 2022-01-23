package br.com.estore.appbff.apibff.domain.orders.query;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderQueryDTO {

    private Long id;

    private StatusDTO status;

    private CustomerDTO customer;

    private AddressDTO shippingAddress;

    private List<ItemDTO> items;

    private BigDecimal price;
}
