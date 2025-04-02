package com.projetos.ecommerce.model;

import com.projetos.ecommerce.listener.GenericoListener;
import com.projetos.ecommerce.listener.GerarNotaFiscalListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@EntityListeners({GenericoListener.class})
@Entity
@Table(name = "produto")
public class Produto extends EntidadeBaseLong{

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens;

    //JoinTable cria uma tabela auxiliar
    //inverseJoinColumns especifica as colunas da tabela auxiliar
    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", insertable = false)
    private LocalDateTime dataAtualizacao;

    //@ElementCollection cria uma tabela auxiliar
    //CollectionTable especifica as colunas da tabela auxiliar
    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;

    @Lob
    private byte[] foto;
}
