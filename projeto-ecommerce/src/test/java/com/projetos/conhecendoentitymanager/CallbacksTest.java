package com.projetos.conhecendoentitymanager;

import com.projetos.ecommerce.model.Cliente;
import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.model.StatusPedido;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallbacksTest extends EntityManagerTest {

    @Test
    public void acionarCallbacks() {
        Cliente cliente = entityManager.find(Cliente.class, 3);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assertions.assertNotNull(pedidoVerificacao.getDataAtualizacao());
    }
}
