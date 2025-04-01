package com.projetos.ecommerce.listener;

import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.service.NotaFiscalService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscalId() == null) {
            notaFiscalService.gerar(pedido);
        }
    }
}
