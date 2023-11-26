package com.test.avaliable.api.v1;

import com.test.avaliable.business.dto.DataAuthenticateDTO;
import com.test.avaliable.business.dto.DataTokenJWTDTO;
import com.test.avaliable.business.service.TokenService;
import com.test.avaliable.fixture.UsuarioEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class AutrenticacaoRestTest {

    @InjectMocks
    AutenticacaoRest autenticacaoRest;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setup(){
        MockMvcBuilders.standaloneSetup(autenticacaoRest).alwaysDo(print()).build();
    }

    @Test
    void autenticacaoLogin(){
        var usuarioEntity = UsuarioEntityFixture.build();
        var dataAuthenticateDTO = new DataAuthenticateDTO("usuario", "senha");
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dataAuthenticateDTO.login(), dataAuthenticateDTO.password());
        Authentication autenticacao = mock(Authentication.class);
        DataTokenJWTDTO dataTokenJWTDTO = new DataTokenJWTDTO("token-gerado");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(autenticacao);
        when(autenticacao.getPrincipal()).thenReturn(usuarioEntity);
        when(tokenService.gerarToken(usuarioEntity)).thenReturn("token-gerado");

        ResponseEntity<DataTokenJWTDTO> responseEntity = autenticacaoRest.autenticacaoLogin(dataAuthenticateDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dataTokenJWTDTO, responseEntity.getBody());

        verify(authenticationManager).authenticate(autenticacaoToken);
        verify(tokenService).gerarToken(usuarioEntity);
    }
}
