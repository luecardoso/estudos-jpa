package com.projetos.mapeamentoavancado;

import com.projetos.ecommerce.model.Produto;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DetalhesColumnTest extends EntityManagerTest {

    @Test
    public void impedirInsercaoDaColunaAtualizacao() {
        Produto produto = new Produto();
        produto.setNome("Teclado para smartphone");
        produto.setDescricao("O mais confortável");
        produto.setPreco(BigDecimal.ONE);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificacao.getDataCriacao());
        Assertions.assertNull(produtoVerificacao.getDataAtualizacao());
    }

    @Test
    public void impedirAtualizacaoDaColunaCriacao() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 3L);
        produto.setPreco(BigDecimal.TEN);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
        Assertions.assertEquals(produto.getDataAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}