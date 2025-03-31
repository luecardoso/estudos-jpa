package aplicacao;

import dominio.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();
        //SAVE
        Pessoa p1 = new Pessoa(1, "Maria", "maria@example.com");
        System.out.println(p1);

        Pessoa p2 = new Pessoa(2, "Joao", "joao@exemplo.com");
        System.out.println(p2);
        Pessoa pessoaManaged = em.merge(p1);
        Pessoa pessoaManaged2 = em.merge(p2);
        em.getTransaction().begin();
        em.persist(pessoaManaged);
        em.persist(pessoaManaged2);
        em.getTransaction().commit();
        //FIND
        Pessoa pessoa = em.find(Pessoa.class, 1);
        System.out.println(pessoa);
        //DELETE
        em.getTransaction().begin();
        em.remove(pessoa);
        em.getTransaction().commit();

        em.close();
        emf.close();
        System.out.println("Persistido com sucesso!");
    }
}
