package br.com.estore.ordercommand.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "postal_code")
    private String postalCode;

}
