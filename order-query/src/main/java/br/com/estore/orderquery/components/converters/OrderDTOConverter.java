package br.com.estore.orderquery.components.converters;

import br.com.estore.orderquery.domain.dtos.*;
import br.com.estore.orderquery.domain.entities.*;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDTOConverter extends AbstractConverter<Order, OrderDTO> {

    @Override
    protected OrderDTO convert(final Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .status(buildStatus(order.getStatus()))
                .customer(buildCostumer(order.getCustomer()))
                .shippingAddress(buildShippingAddress(order.getShippingAddress()))
                .items(buildItems(order.getItems()))
                .price(order.getTotal())
                .build();
    }

    private StatusDTO buildStatus(final Status status) {
        return Optional.ofNullable(status)
                .map(s ->
                    StatusDTO.builder()
                             .code(s.getCode())
                             .description(s.getDescription())
                             .build()
                )
                .orElse(null);
    }

    private CustomerDTO buildCostumer(final Customer costumer) {
        return CustomerDTO.builder()
                .documentNumber(costumer.getDocumentNumber())
                .email(costumer.getEmail())
                .name(costumer.getName())
                .build();
    }

    private AddressDTO buildShippingAddress(final Address address) {
        return AddressDTO.builder()
                .additionalInfo(address.getAdditionalInfo())
                .number(address.getNumber())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }

    private List<ItemDTO> buildItems(final List<Item> items) {
        return items.stream()
                .map(this::buildItem)
                .collect(Collectors.toList());
    }

    private ItemDTO buildItem(final Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .code(item.getCode())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }
}
