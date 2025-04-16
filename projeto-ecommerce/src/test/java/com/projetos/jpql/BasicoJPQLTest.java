package com.projetos.jpql;

import com.projetos.ecommerce.model.Pedido;
import com.projetos.util.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        // JPQL - Java Persistence Query Language
        // JPQL - select p from Pedido p where p.id = 1
        // SQL - select p.* from pedido where id = 1

        // entityManager.find(Pedido.class, 1)
        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        Assert.assertFalse(lista.isEmpty());
    }
}
