package br.com.estore.orderquery.domain.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Order {

    private Long id;

    private Status status;

    private Customer customer;

    private Address shippingAddress;

    private List<Item> items;

    public BigDecimal getTotal() {
        return items.stream()
                .map(Item::getPrice)
                .reduce(this::sum)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal sum(final BigDecimal aggregate, final BigDecimal current) {
        return aggregate.add(current);
    }

}
