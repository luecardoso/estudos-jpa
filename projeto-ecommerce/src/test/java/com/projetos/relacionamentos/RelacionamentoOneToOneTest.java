package com.projetos.relacionamentos;

import com.projetos.ecommerce.model.NotaFiscal;
import com.projetos.ecommerce.model.PagamentoCartao;
import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.model.StatusPagamento;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 2);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumeroCartao("1234");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    public void verificarRelacionamentoPedidoNotaFiscal() {
        Pedido pedido = entityManager.find(Pedido.class, 2);

        NotaFiscal notaFiscal = new NotaFiscal();
//        notaFiscal.setXml("TESTE");
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getNotaFiscalId());
    }
}