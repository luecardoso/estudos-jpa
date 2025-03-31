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
@Table(name = "estoque")
public class Estoque {
    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @Column(name = "produto_id")
    private Integer produtoId;
    private Integer quantidade;
}
