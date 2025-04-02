package com.projetos.conhecendoentitymanager;

import com.projetos.ecommerce.model.Categoria;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        Categoria categoriaNovo = new Categoria();

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 2);

        entityManager.remove(categoriaGerenciada);
        entityManager.persist(categoriaGerenciada);

        entityManager.detach(categoriaGerenciada);
    }

}

