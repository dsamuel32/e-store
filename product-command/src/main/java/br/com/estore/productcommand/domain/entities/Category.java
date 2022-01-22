package br.com.estore.productcommand.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Category {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

}
