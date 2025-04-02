package com.projetos.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@SecondaryTable(name = "cliente_detalhe",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
        foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
@Entity
@Table(name = "cliente",
        uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = { "cpf" }) },
        indexes = { @Index(name = "idx_nome", columnList = "nome") })
public class Cliente extends EntidadeBaseLong {

    @Column(length = 100, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(table = "cliente_detalhe")
    private SexoCliente sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @Transient
    private String primeiroNome;

    @PostLoad
    public void configurarPrimeiroNome(){
        if (nome != null && !nome.trim().isEmpty()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }

    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @Column(length = 14, nullable = false)
    private String cpf;
}
