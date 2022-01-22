package br.com.estore.productquery.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "products")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String brand;

    private Category category;

    private List<Image> images;

}
