package br.com.estore.productquery.domain.entities;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Image {

    private String link;

    private String description;

    private Boolean main = false;

}
