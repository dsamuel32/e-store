package br.com.estore.ordercommand.domain.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Status {

    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

}
