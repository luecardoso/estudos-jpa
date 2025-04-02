package com.projetos.mapeamentoavancado;

import com.projetos.ecommerce.model.Cliente;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome() {
        Cliente cliente = entityManager.find(Cliente.class, 2);
        System.out.println(cliente.getPrimeiroNome());
        Assertions.assertEquals("Felipe", cliente.getPrimeiroNome());
    }
}
