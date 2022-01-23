package br.com.estore.orderquery.domain.entities;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Customer {

    private String name;

    private String documentNumber;

    private String email;

}
