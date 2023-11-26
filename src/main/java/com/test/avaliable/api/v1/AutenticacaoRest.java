package com.test.avaliable.api.v1;

import com.test.avaliable.business.dto.DataAuthenticateDTO;
import com.test.avaliable.business.dto.DataTokenJWTDTO;
import com.test.avaliable.business.service.TokenService;
import com.test.avaliable.infrastructure.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.test.avaliable.infrastructure.utils.URLConstantes.AUTHENTICATION_URL;


@Slf4j
@RestController
@RequestMapping(path = AUTHENTICATION_URL)
@Tag(name = "API - Controle de Autenticação", description = "Gerencimaneto de Autenticação")
@RequiredArgsConstructor
public class AutenticacaoRest {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Autenticar usuário")
    @PostMapping
    public ResponseEntity<DataTokenJWTDTO> autenticacaoLogin(@RequestBody @Valid DataAuthenticateDTO dataAuthenticateDTO){
        log.info("Autenticando usuário de login: {}", dataAuthenticateDTO.login());

        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dataAuthenticateDTO.login(), dataAuthenticateDTO.password());
        var autenticacao = authenticationManager.authenticate(autenticacaoToken);

        return ResponseEntity.ok(new DataTokenJWTDTO(tokenService.gerarToken((UsuarioEntity) autenticacao.getPrincipal())));
    }
}
