package br.com.estore.ordercommand.components.converters;

import br.com.estore.ordercommand.domain.dtos.AddressDTO;
import br.com.estore.ordercommand.domain.dtos.CustomerDTO;
import br.com.estore.ordercommand.domain.dtos.ItemDTO;
import br.com.estore.ordercommand.domain.dtos.OrderDTO;
import br.com.estore.ordercommand.domain.entities.Address;
import br.com.estore.ordercommand.domain.entities.Customer;
import br.com.estore.ordercommand.domain.entities.Item;
import br.com.estore.ordercommand.domain.entities.Order;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderEntityConverter extends AbstractConverter<OrderDTO, Order> {

    @Override
    protected Order convert(final OrderDTO order) {
        return Order.builder()
                .customer(buildCostumer(order.getCustomer()))
                .shippingAddress(buildShippingAddress(order.getShippingAddress()))
                .items(buildItems(order.getItems()))
                .published(false)
                .build();
    }

    private Customer buildCostumer(final CustomerDTO costumer) {
        return Customer.builder()
                .id(costumer.getId())
                .documentNumber(costumer.getDocumentNumber())
                .email(costumer.getEmail())
                .name(costumer.getName())
                .build();
    }

    private Address buildShippingAddress(final AddressDTO address) {
        return Address.builder()
                .id(address.getId())
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
