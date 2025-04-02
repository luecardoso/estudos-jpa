package com.projetos.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseLong{

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
}
