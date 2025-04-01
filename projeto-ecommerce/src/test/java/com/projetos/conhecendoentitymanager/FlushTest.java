package com.projetos.conhecendoentitymanager;

import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.model.StatusPedido;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlushTest extends EntityManagerTest {

    @Test
    public void chamarFlush() {
        Assertions.assertThrows(Exception.class, () -> {
            try {
                entityManager.getTransaction().begin();

                Pedido pedido = entityManager.find(Pedido.class, 1);
                pedido.setStatus(StatusPedido.PAGO);

                entityManager.flush();

                if (pedido.getPagamento() == null) {
                    throw new RuntimeException("Pedido ainda não foi pago.");
                }

    //            Uma consulta obriga o JPA a sincronizar o que ele tem na memória (sem usar o flush explicitamente).
    //            Pedido pedidoPago = entityManager
    //                    .createQuery("select p from Pedido p where p.id = 1", Pedido.class)
    //                    .getSingleResult();
    //            Assert.assertEquals(pedido.getStatus(), pedidoPago.getStatus());

                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw e;
            }

        });
    }
}