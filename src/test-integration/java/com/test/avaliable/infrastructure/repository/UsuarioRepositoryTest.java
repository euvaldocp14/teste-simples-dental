package com.test.avaliable.infrastructure.repository;

import com.test.avaliable.JpaTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioRepositoryTest extends JpaTestBase {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar o usuário salvo na base.")
    @Sql("classpath:dbScripts/insere_usuario.sql")
    void testBuscarListaUsuario() {
        var login = "teste123";

        var resultado = usuarioRepository.findByLogin(login);

        assertEquals(login, resultado.getUsername());
    }
}
