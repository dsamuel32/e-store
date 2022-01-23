package br.com.estore.appbff.apibff.domain.orders.query;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CustomerDTO {

    private Long id;

    private String name;

    private String documentNumber;

    private String email;

}
