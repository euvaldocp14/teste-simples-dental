package com.test.avaliable.api.v1;

import com.test.avaliable.business.dto.ContatoDTO;
import com.test.avaliable.business.dto.ContatoRequestDTO;
import com.test.avaliable.business.dto.ContatoResponseDTO;
import com.test.avaliable.business.service.ContatoService;
import com.test.avaliable.config.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.test.avaliable.infrastructure.utils.URLConstantes.BASE_URL_CONTATO;

@Slf4j
@RestController
@RequestMapping(path = BASE_URL_CONTATO)
@Tag(name = "API - Contato", description = "Gerenciamento de Contato")
@SecurityRequirement(name = SecurityConfigurations.SECURITY_SCHEME)
@RequiredArgsConstructor
public class ContatoRest {

    private final ContatoService contatoService;

    @Operation(summary = "Adicionar contato na base de dados")
    @PostMapping
    public ResponseEntity<ContatoResponseDTO> adicionarContato(@RequestBody @Valid ContatoRequestDTO contatoRequestDTO,
                                                               UriComponentsBuilder uriBuilder) {
        log.info("Adicionando o contato");

        var contatoResponseDTO = contatoService.adicionarContato(contatoRequestDTO);

        URI uri = uriBuilder.path("/avaliacao/v1/contatos/{id}").buildAndExpand(contatoResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(contatoResponseDTO);
    }

    @Operation(summary = "Buscar contato na base de dados")
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarContato(@PathVariable("id") String id) {
        log.info("Buscando contato de id: {}", id);

        var contactDTO = contatoService.buscarContato(id);

        return ResponseEntity.ok(contactDTO);
    }

    @Operation(summary = "Alterando o contato na base de dados")
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarContato(@PathVariable("id") String id,
                                               @RequestBody @Valid ContatoRequestDTO contatoRequestDTO) {
        log.info("Alterando o contato de id: {}", id);

        contatoService.alterarContato(id, contatoRequestDTO);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Exclir contato na base de dados")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirContato(@PathVariable("id") String id) {
        log.info("Excluindo o contato de id: {}", id);

        contatoService.deletarContato(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Buscar lista de contato na base de dados")
    @GetMapping
    public ResponseEntity<List<ContatoDTO>> buscarListaContato(@RequestParam(value = "filtro") String filtro,
                                                               @RequestParam(value = "fields", required = false) List<String> campos,
                                                               Pageable paginacao) {
        log.info("Buscando lista de contato.");

        var contatoDTOList = contatoService.buscarListaContato(filtro, campos, paginacao);

        return ResponseEntity.ok(contatoDTOList);
    }
}
