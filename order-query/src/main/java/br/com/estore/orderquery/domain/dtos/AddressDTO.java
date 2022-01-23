package br.com.estore.orderquery.domain.dtos;

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
