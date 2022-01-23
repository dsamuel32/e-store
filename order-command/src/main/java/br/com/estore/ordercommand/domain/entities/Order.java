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
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id", nullable = false)
    private List<Item> items;

    @Column(name = "published")
    private Boolean published;

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
