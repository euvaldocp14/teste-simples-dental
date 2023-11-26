package com.test.avaliable.infrastructure.repository;

import com.test.avaliable.JpaTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfissionalRepositoryTest extends JpaTestBase {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Test
    @DisplayName("Deve retornar os profissionais salvos na base.")
    @Sql("classpath:dbScripts/insere_profissional.sql")
    void testBuscarListaProfissionais() {
        var filtro = "Costa";

        var pageable = PageRequest.of(0, 10);

        var resultado = profissionalRepository.buscarListaProfissional(filtro, pageable);

        assertEquals(1, resultado.size());
    }
}
