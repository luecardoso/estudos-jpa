package com.projetos.conhecendoentitymanager;

import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.model.StatusPedido;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test
    public void abrirFecharCancelarTransacao() {
        Assertions.assertThrows(Exception.class, () ->{
            try {
                entityManager.getTransaction().begin();
                metodoDeNegocio();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw e;
            }
        });
    }

    private void metodoDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda não foi pago.");
        }
    }
}
