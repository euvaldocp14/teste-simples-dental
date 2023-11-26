package com.test.avaliable.business.service;

import com.test.avaliable.business.dto.ProfissionalDTO;
import com.test.avaliable.business.dto.ProfissionalRequestDTO;
import com.test.avaliable.business.dto.ProfissionalResponseDTO;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import com.test.avaliable.infrastructure.exception.ResourceNotFoundException;
import com.test.avaliable.infrastructure.mappers.ProfissionalMapper;
import com.test.avaliable.infrastructure.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_CONTATO_NAO_ENCONTRADO;
import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_PROFISSIONAL_NAO_ENCONTRADO;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;

    public ProfissionalDTO buscarProfissional(String id) {
        var profissionalEntity = profissionalRepository.findById(UUID.fromString(id));

        var profissionalDTO = profissionalMapper.profissionalEntityToDTO(profissionalEntity
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id))));

        log.info("Profissional de nome {} retornado", profissionalDTO.getNome());

        return profissionalDTO;
    }

    public ProfissionalResponseDTO adicionarProfissional(ProfissionalRequestDTO profissionalRequestDTO) {
        var profissionalEntity = profissionalMapper.profissionalRequestDTOToEntity(profissionalRequestDTO);
        profissionalEntity.setId(UUID.randomUUID());
        profissionalEntity.setDataCriacao(LocalDate.now());
        profissionalEntity.setAtivo(true);

        profissionalRepository.save(profissionalEntity);

        log.info("Profissional de id {} adicionado", profissionalEntity.getId());

        return profissionalMapper.profissionalEntityToResponseDTO(profissionalEntity);
    }

    public void alterarProfissional(String id, ProfissionalRequestDTO profissionalRequestDTO) {
        var profissionalEntity = profissionalRepository.findById(UUID.fromString(id))
                .map(profissional -> atualizarProfissionalEntity(profissional, profissionalRequestDTO))
                .map(this::salvarProfissional)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id)));

        log.info("Atualizando o profissional de id: {}", profissionalEntity.getId());
    }

    public void deletarProfissional(String id) {
        profissionalRepository.findById(UUID.fromString(id))
                .map(this::setStatus)
                .map(this::salvarProfissional)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id)));

        log.info("Deletado o profissional de id: {}", id);
    }

    public List<ProfissionalDTO> buscarListaProfissional(String filtro, List<String> campos, Pageable paginacao) {
        var listaProfissionalEntity = profissionalRepository.buscarListaProfissional(filtro, paginacao);
        var profissionalDTO = profissionalMapper.profissionalEntityToDTOLista(listaProfissionalEntity);

        if (campos != null && !campos.isEmpty()) {
            profissionalDTO = profissionalDTO
                    .stream()
                    .map(dto -> filtrarAtributos(dto, campos))
                    .collect(Collectors.toList());
        }

        log.info("Lista de profissionais retornada.");

        return profissionalDTO;
    }

    private ProfissionalEntity atualizarProfissionalEntity(ProfissionalEntity profissionalEntity, ProfissionalRequestDTO profissionalRequestDTO) {
        profissionalEntity.setNome(profissionalRequestDTO.getNome());
        profissionalEntity.setCargo(profissionalRequestDTO.getCargo());
        profissionalEntity.setDataNascimento(profissionalRequestDTO.getDataNascimento());
        profissionalEntity.setDataCriacao(LocalDate.now());

        return profissionalEntity;
    }

    private ProfissionalEntity setStatus(ProfissionalEntity profissionalEntity) {
        profissionalEntity.setAtivo(false);
        profissionalEntity.setDataCriacao(LocalDate.now());

        return profissionalEntity;
    }

    private ProfissionalEntity salvarProfissional(ProfissionalEntity profissionalEntity) {
        return profissionalRepository.save(profissionalEntity);
    }

    private ProfissionalDTO filtrarAtributos(ProfissionalDTO profissionalDTO, List<String> atributosDesejados) {
        ProfissionalDTO profissional = new ProfissionalDTO();

        // Iterar sobre os atributos desejados e copiar do DTO original para o novo DTO
        for (String atributo : atributosDesejados) {
            switch (atributo) {
                case "nome":
                    profissional.setNome(profissionalDTO.getNome());
                    break;
                case "ativo":
                    profissional.setAtivo(profissionalDTO.getAtivo());
                    break;
                case "cargo":
                    profissional.setCargo(profissionalDTO.getCargo());
                    break;
                case "dataCriacao":
                    profissional.setDataCriacao(profissionalDTO.getDataCriacao());
                    break;
                case "dataNascimento":
                    profissional.setDataNascimento(profissionalDTO.getDataNascimento());
                    break;
            }
        }

        return profissional;
    }


}
