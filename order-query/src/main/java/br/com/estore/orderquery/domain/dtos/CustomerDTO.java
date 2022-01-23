package br.com.estore.orderquery.domain.dtos;

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
