package br.com.estore.orderquery.domain.dtos;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PaymentDTO {

    private String name;

    private Integer creditCartNumber;

    private String expirationDate;

    private Integer verificationCode;

}
