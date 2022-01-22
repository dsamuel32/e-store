package br.com.estore.productcommand.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "main")
    private Boolean main = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
