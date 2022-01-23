package br.com.estore.appbff.apibff.domain.orders.command;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StatusDTO {

    private String code;

    private String description;

}
