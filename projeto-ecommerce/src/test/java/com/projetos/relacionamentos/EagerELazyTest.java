package com.projetos.relacionamentos;

import com.projetos.ecommerce.model.Pedido;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class EagerELazyTest extends EntityManagerTest {

    @Test
    public void verficarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);


        pedido.getItens().isEmpty();
    }
}