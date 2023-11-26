package com.test.avaliable.infrastructure.repository;

import com.test.avaliable.JpaTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoRepositoryTest extends JpaTestBase {

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    @DisplayName("Deve retornar os contatos salvos na base.")
    @Sql("classpath:dbScripts/insere_contato.sql")
    void testBuscarListaContatos() {
        var filtro = "FIXO";

        var pageable = PageRequest.of(0, 10);

        var resultado = contatoRepository.buscarListaContato(filtro, pageable);

        assertEquals(filtro, resultado.get(0).getNome().toString());
    }
}
