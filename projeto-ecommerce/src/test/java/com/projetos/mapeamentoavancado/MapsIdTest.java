package com.projetos.mapeamentoavancado;

import com.projetos.ecommerce.model.*;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class MapsIdTest extends EntityManagerTest {

    @Test
    public void inserirPagamento() {
        Pedido pedido = entityManager.find(Pedido.class, 2);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
//        notaFiscal.setXml("<xml/>");

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVarificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVarificacao);
        Assertions.assertEquals(pedido.getId(), notaFiscalVarificacao.getId());
    }

    @Test
    public void inserirItemPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 2);
        Produto produto = entityManager.find(Produto.class, 3);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(
                ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
        Assertions.assertNotNull(itemPedidoVerificacao);
    }
}
