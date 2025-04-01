package com.projetos.ecommerce.service;

import com.projetos.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido) {
        System.out.println("Gerando nota para o pedido " + pedido.getId() + ".");
    }
}
