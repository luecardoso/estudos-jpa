package com.projetos.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("boleto")
@Entity
//@Table(name = "pagamento_boleto") // usado com @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PagamentoBoleto extends Pagamento{

    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;
}

