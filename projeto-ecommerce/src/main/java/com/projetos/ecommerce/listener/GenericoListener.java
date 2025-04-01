package com.projetos.ecommerce.listener;

import jakarta.persistence.PostLoad;

public class GenericoListener {

    //PostLoad e PostPersist sao listeners que serao executados quando a entidade for carregada ou persistida
    @PostLoad
    public void logCarregamento(Object obj) {
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
    }
}
