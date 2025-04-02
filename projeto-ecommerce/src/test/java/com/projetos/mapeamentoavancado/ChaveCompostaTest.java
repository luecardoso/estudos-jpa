package com.projetos.mapeamentoavancado;

import com.projetos.ecommerce.model.*;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ChaveCompostaTest extends EntityManagerTest {

    @Test
    public void salvarItem() {
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 2);
        Produto produto = entityManager.find(Produto.class, 3);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        entityManager.persist(pedido);

        entityManager.flush();

        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setPedidoId(pedido.getId()); //Idclass
//        itemPedido.setProdutoId(produto.getId()); // IdClass
        itemPedido.setId(new ItemPedidoId(produto.getId(), pedido.getId()));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);

        entityManager.persist(itemPedido);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void bucarItem() {
        ItemPedido itemPedido = entityManager.find(
                ItemPedido.class, new ItemPedidoId(2L, 3L));

        Assertions.assertNotNull(itemPedido);
    }
}
