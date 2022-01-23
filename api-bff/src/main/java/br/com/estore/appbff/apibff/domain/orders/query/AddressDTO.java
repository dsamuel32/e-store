package br.com.estore.appbff.apibff.domain.orders.query;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDTO {

    private Long id;

    private String street;

    private String number;

    private String additionalInfo;

    private String postalCode;

}
