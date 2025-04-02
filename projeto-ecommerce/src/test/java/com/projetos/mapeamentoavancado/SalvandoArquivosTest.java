package com.projetos.mapeamentoavancado;

import com.projetos.ecommerce.model.NotaFiscal;
import com.projetos.ecommerce.model.Pedido;
import com.projetos.ecommerce.model.Produto;
import com.projetos.util.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarFotoProduto() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 3);
        produto.setFoto(carregarFoto());
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assertions.assertNotNull(produtoVerificacao.getFoto());
        Assertions.assertTrue(produtoVerificacao.getFoto().length > 0);
    }

    @Test
    public void salvarXmlNota() {
        Pedido pedido = entityManager.find(Pedido.class, 2);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificacao.getXml());
        Assertions.assertTrue(notaFiscalVerificacao.getXml().length > 0);


//        try {
//            OutputStream out = new FileOutputStream(
//                    Files.createFile(Paths.get(
//                            System.getProperty("user.home") + "/xml.xml")).toFile());
//            out.write(notaFiscalVerificacao.getXml());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

    private static byte[] carregarFoto() {
        return carregarArquivo("src/test/resources/kindle.jpg");
    }

    private static byte[] carregarNotaFiscal() {
        return carregarArquivo("src/test/resources/nota-fiscal.xml");
    }

    private static byte[] carregarArquivo(String nome) {
        try(InputStream inputStream = SalvandoArquivosTest.class.getResourceAsStream(nome)) {
            return Files.readAllBytes(Paths.get(nome));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}