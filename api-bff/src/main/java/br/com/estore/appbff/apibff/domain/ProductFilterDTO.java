package br.com.estore.appbff.apibff.domain;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDTO {

    private String name;
    private String description;
    private String category;
    @Builder.Default
    private Integer pageNumber = 1;
    @Builder.Default
    private Integer itemsPerPage = 10;

}
