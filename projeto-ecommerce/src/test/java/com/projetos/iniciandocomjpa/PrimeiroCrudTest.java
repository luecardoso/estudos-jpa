package com.projetos.iniciandocomjpa;

import com.projetos.ecommerce.model.Cliente;
import com.projetos.ecommerce.model.Produto;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void atualizarRegistro() {
        Cliente cliente = new Cliente();

        cliente.setId(2L);
        cliente.setNome("Felipe Reis");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertEquals("Felipe Reis", clienteVerificacao.getNome());
    }

    @Test
    public void busarPorIdentificador() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals("Felipe Reis", cliente.getNome());
    }

    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();

        //cliente.setId(3L);
        cliente.setNome("Lucas");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
    }

    @Test
    public void removerRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNull(clienteVerificacao);
    }

}
