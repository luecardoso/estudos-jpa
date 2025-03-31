package com.projetos.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "categoria")
public class Categoria {
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

}
