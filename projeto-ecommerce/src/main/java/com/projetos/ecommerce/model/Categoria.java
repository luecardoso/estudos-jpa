package com.projetos.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categoria",
        uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) })
public class Categoria extends EntidadeBaseLong{

    @Column(length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_pai_id",
            foreignKey = @ForeignKey(name = "fk_categoria_categoriapai"))
    private Categoria categoriaPaiId;

    @OneToMany(mappedBy = "categoriaPaiId")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

}
