package br.com.estore.ordercommand.domain.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "costumers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "email")
    private String email;

}
