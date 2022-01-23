package br.com.estore.ordercommand.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(columnDefinition = "order_id")
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
