package br.com.estore.orderquery.components.converters;

import br.com.estore.orderquery.domain.dtos.*;
import br.com.estore.orderquery.domain.entities.*;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEntityConverter extends AbstractConverter<OrderDTO, Order> {

    @Override
    protected Order convert(final OrderDTO order) {
        return Order.builder()
                .id(order.getId())
                .status(buildStatus(order.getStatus()))
                .customer(buildCostumer(order.getCustomer()))
                .shippingAddress(buildShippingAddress(order.getShippingAddress()))
                .items(buildItems(order.getItems()))
                .build();
    }

    private Status buildStatus(final StatusDTO status) {
        return Status.builder()
                .code(status.getCode())
                .description(status.getDescription())
                .build();
    }

    private Customer buildCostumer(final CustomerDTO costumer) {
        return Customer.builder()
                .documentNumber(costumer.getDocumentNumber())
                .email(costumer.getEmail())
                .name(costumer.getName())
                .build();
    }

    private Address buildShippingAddress(final AddressDTO address) {
        return Address.builder()
                .additionalInfo(address.getAdditionalInfo())
                .number(address.getNumber())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }

    private List<Item> buildItems(final List<ItemDTO> items) {
        return items.stream()
                .map(this::buildItem)
                .collect(Collectors.toList());
    }

    private Item buildItem(final ItemDTO item) {
        return Item.builder()
                .id(item.getId())
                .code(item.getCode())
                .price(item.getPrice())
                .name(item.getName())
                .build();
    }

}
