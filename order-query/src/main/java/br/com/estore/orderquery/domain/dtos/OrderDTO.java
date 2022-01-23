package br.com.estore.orderquery.domain.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderDTO {

    private Long id;

    private StatusDTO status;

    private CustomerDTO customer;

    private AddressDTO shippingAddress;

    private List<ItemDTO> items;

    private BigDecimal price;
}
