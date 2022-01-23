package br.com.estore.orderquery.domain.entities;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Address {

    private String street;

    private String number;

    private String additionalInfo;

    private String postalCode;

}
