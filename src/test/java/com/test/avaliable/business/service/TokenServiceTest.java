package com.test.avaliable.business.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.test.avaliable.fixture.UsuarioEntityFixture;
import com.test.avaliable.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Test
    void loadUserByUsername(){
        var usuarioEntity = UsuarioEntityFixture.buildNomeUsuario("teste");

        tokenService.setSecret("12345678");

        var retorno = tokenService.gerarToken(usuarioEntity);

        var decodedToken = JWT.require(Algorithm.HMAC256(tokenService.getSecret())).build().verify(retorno);

        assertEquals("teste", decodedToken.getSubject());
    }

}
