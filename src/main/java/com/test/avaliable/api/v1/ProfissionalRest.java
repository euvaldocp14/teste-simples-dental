package com.test.avaliable.api.v1;

import com.test.avaliable.business.dto.ProfissionalDTO;
import com.test.avaliable.business.dto.ProfissionalRequestDTO;
import com.test.avaliable.business.dto.ProfissionalResponseDTO;
import com.test.avaliable.business.service.ProfissionalService;
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

import static com.test.avaliable.infrastructure.utils.URLConstantes.BASE_URL_PROFISSIONAL;

@Slf4j
@RestController
@RequestMapping(path = BASE_URL_PROFISSIONAL)
@Tag(name = "API - Profissional", description = "Gerenciamento de Profissional")
@SecurityRequirement(name = SecurityConfigurations.SECURITY_SCHEME)
@RequiredArgsConstructor
public class ProfissionalRest {

    private final ProfissionalService profissionalService;

    @Operation(summary = "Buscar lista de profissional na base de dados")
    @GetMapping
    public ResponseEntity<List<ProfissionalDTO>> buscarListaProfissional(@RequestParam(value = "filtro") String filtro,
                                                                         @RequestParam(value = "fields", required = false) List<String> campos,
                                                                         Pageable paginacao) {
        log.info("Buscando lista de profissional.");

        var profissionalDTO = profissionalService.buscarListaProfissional(filtro, campos, paginacao);

        return ResponseEntity.ok(profissionalDTO);
    }

    @Operation(summary = "Buscar profissional na base de dados")
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> buscarProfissional(@PathVariable("id") String id) {
        log.info("Buscando profissional de id: {}", id);

        var profissionalDTO = profissionalService.buscarProfissional(id);

        return ResponseEntity.ok(profissionalDTO);
    }

    @Operation(summary = "Adicionar profissional na base de dados")
    @PostMapping
    public ResponseEntity<ProfissionalResponseDTO> adicionarProfissional(@RequestBody @Valid ProfissionalRequestDTO profissionalDTO,
                                                                         UriComponentsBuilder uriBuilder) {
        log.info("Adicionando o profissional: {}", profissionalDTO);

        var professionalResponseDTO = profissionalService.adicionarProfissional(profissionalDTO);

        URI uri = uriBuilder.path("/avaliacao/v1/profissionais/{id}").buildAndExpand(professionalResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(professionalResponseDTO);
    }

    @Operation(summary = "Alterando o profissional na base de dados")
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarProfissional(@PathVariable("id") String id,
                                                    @RequestBody @Valid ProfissionalRequestDTO profissionalDTO) {
        log.info("Alterando o profissional de id: {}", id);

        profissionalService.alterarProfissional(id, profissionalDTO);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Exclir profissional na base de dados")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfissional(@PathVariable("id") String id) {
        log.info("Excluindo o profissional de id: {}", id);

        profissionalService.deletarProfissional(id);

        return ResponseEntity.ok().build();
    }
}
