package br.com.estore.ordercommand.domain.dtos;

import br.com.estore.ordercommand.constants.ApplicationConstants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Valid
    private CustomerDTO customer;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Valid
    private AddressDTO shippingAddress;

    @NotNull(message = ApplicationConstants.REQUIRED_NOT_EMPTY_FIELD)
    @Valid
    private List<ItemDTO> items;

    private BigDecimal price;
}
