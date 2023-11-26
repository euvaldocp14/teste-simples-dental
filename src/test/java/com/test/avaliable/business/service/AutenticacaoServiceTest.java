package com.test.avaliable.business.service;

import com.test.avaliable.fixture.UsuarioEntityFixture;
import com.test.avaliable.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void loadUserByUsername(){
        var usuarioEntity = UsuarioEntityFixture.buildNomeUsuario("teste");

        when(usuarioRepository.findByLogin(usuarioEntity.getLogin())).thenReturn(usuarioEntity);

        var retorno = autenticacaoService.loadUserByUsername(usuarioEntity.getLogin());

        verify(usuarioRepository).findByLogin(usuarioEntity.getLogin());

        assertEquals(usuarioEntity.getLogin(), retorno.getUsername());
    }

}
