package com.test.avaliable.business.service;

import com.test.avaliable.business.dto.ContatoDTO;
import com.test.avaliable.business.dto.ContatoRequestDTO;
import com.test.avaliable.business.dto.ContatoResponseDTO;
import com.test.avaliable.infrastructure.entity.ContatoEntity;
import com.test.avaliable.infrastructure.exception.BusinessException;
import com.test.avaliable.infrastructure.exception.ResourceNotFoundException;
import com.test.avaliable.infrastructure.mappers.ContatoMapper;
import com.test.avaliable.infrastructure.repository.ContatoRepository;
import com.test.avaliable.infrastructure.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.test.avaliable.infrastructure.utils.Constantes.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ContatoMapper contatoMapper;

    public ContatoDTO buscarContato(String id) {
        var contatoEntity = contatoRepository.findById(UUID.fromString(id));

        var contatoDTO = contatoMapper.contatoEntityToDTO(contatoEntity
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_CONTATO_NAO_ENCONTRADO, id))));

        log.info("Contato de ID {} encontrado.", contatoEntity.get().getId());

        return contatoDTO;
    }

    public ContatoResponseDTO adicionarContato(ContatoRequestDTO contatoRequestDTO) {
        var contatoEntity = contatoMapper.contatoRequestDTOToEntity(contatoRequestDTO);
        contatoEntity.setId(UUID.randomUUID());
        contatoEntity.setDataCriacao(LocalDate.now());
        contatoEntity.setProfissionalEntity(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())
                .orElseThrow(() -> new BusinessException(ERRO_PROFISSIONAL_NAO_PODE_SER_NULO)));

        contatoRepository.save(contatoEntity);

        log.info("Contato de id {} adicionado", contatoEntity.getId());

        return contatoMapper.contatoEntityToDTOResponse(contatoEntity);
    }

    public void alterarContato(String id, ContatoRequestDTO contatoRequestDTO) {
        contatoRepository.findById(UUID.fromString(id))
                .map(contato -> {
                    atualizarContatoEntity(contato, contatoRequestDTO);
                    salvarContato(contato);

                    log.info("Atualizando o contato de id: {}", contato.getId());

                    return contato;
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_CONTATO_NAO_ENCONTRADO, id)));
    }


    public void deletarContato(String id) {
        contatoRepository.findById(UUID.fromString(id))
                .map(contato -> {
                    excluirContato(contato);

                    log.info("Deletado o contato de id: {}", contato.getId());

                    return contato;
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_CONTATO_NAO_ENCONTRADO, id)));
    }

    public List<ContatoDTO> buscarListaContato(String filtro, List<String> campos, Pageable paginacao) {
        var contatoEntityList = contatoRepository.buscarListaContato(filtro, paginacao);
        var contatoDTOList = contatoMapper.contatoEntityToDTOLista(contatoEntityList);

        if (campos != null && !campos.isEmpty()) {
            contatoDTOList = contatoDTOList
                    .stream()
                    .map(dto -> filtrarAtributos(dto, campos))
                    .collect(Collectors.toList());
        }

        log.info("Lista de contatos retornada.");

        return contatoDTOList;
    }

    private ContatoEntity atualizarContatoEntity(ContatoEntity contatoEntity, ContatoRequestDTO contatoRequestDTO) {
        contatoEntity.setNome(contatoRequestDTO.getNome());
        contatoEntity.setContato(contatoRequestDTO.getContato());
        contatoEntity.setDataCriacao(LocalDate.now());
        contatoEntity.setProfissionalEntity(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ERRO_CONTATO_NAO_ENCONTRADO, contatoEntity.getProfissionalEntity().getId()))));

        return contatoEntity;
    }

    private ContatoEntity salvarContato(ContatoEntity contatoEntity) {
        return contatoRepository.save(contatoEntity);
    }

    private void excluirContato(ContatoEntity contatoEntity) {
        contatoRepository.delete(contatoEntity);
    }

    private ContatoDTO filtrarAtributos(ContatoDTO contatoDTO, List<String> atributosDesejados) {
        ContatoDTO contato = new ContatoDTO();

        // Iterar sobre os atributos desejados e copiar do DTO original para o novo DTO
        for (String atributo : atributosDesejados) {
            switch (atributo) {
                case "nome":
                    contato.setNome(contatoDTO.getNome());
                    break;
                case "contato":
                    contato.setContato(contatoDTO.getContato());
                    break;
                case "dataCriacao":
                    contato.setDataCriacao(contatoDTO.getDataCriacao());
                    break;
                case "nomeProfissional":
                    contato.setNomeProfissional(contatoDTO.getNomeProfissional());
                    break;
            }
        }

        return contato;
    }


}
