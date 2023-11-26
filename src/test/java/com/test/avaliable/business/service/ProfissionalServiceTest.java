package com.test.avaliable.business.service;

import com.test.avaliable.fixture.ProfissionalDTOFixture;
import com.test.avaliable.fixture.ProfissionalEntityFixture;
import com.test.avaliable.fixture.ProfissionalRequestDTOFixture;
import com.test.avaliable.fixture.ProfissionalResponseDTOFixture;
import com.test.avaliable.infrastructure.exception.ResourceNotFoundException;
import com.test.avaliable.infrastructure.mappers.ProfissionalMapper;
import com.test.avaliable.infrastructure.repository.ProfissionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_CONTATO_NAO_ENCONTRADO;
import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_PROFISSIONAL_NAO_ENCONTRADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfissionalServiceTest {

    @InjectMocks
    private ProfissionalService profissionalService;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private ProfissionalMapper profissionalMapper;

    @Test
    void buscarProfissional(){
        var id = UUID.randomUUID().toString();
        var profissionalEntity = ProfissionalEntityFixture.build();
        var profissionalDTO = ProfissionalDTOFixture.build();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(profissionalEntity));
        when(profissionalMapper.profissionalEntityToDTO(profissionalEntity)).thenReturn(profissionalDTO);

        var retorno = profissionalService.buscarProfissional(id);

        verify(profissionalRepository).findById(UUID.fromString(id));
        verify(profissionalMapper).profissionalEntityToDTO(profissionalEntity);

        assertEquals(profissionalDTO.getNome(), retorno.getNome());
        assertEquals(profissionalDTO.getCargo(), retorno.getCargo());
        assertEquals(profissionalDTO.getAtivo(), retorno.getAtivo());
        assertEquals(profissionalDTO.getDataNascimento(), retorno.getDataNascimento());
        assertEquals(profissionalDTO.getDataCriacao(), retorno.getDataCriacao());
    }

    @Test
    void buscarProfissionalNaoEncontrado(){
        var id = UUID.randomUUID().toString();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> profissionalService.buscarProfissional(id));

        verify(profissionalRepository).findById(UUID.fromString(id));

        assertEquals(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id), exception.getMessage());
    }

    @Test
    void adicionarProfissional(){
        var profissionalEntity = ProfissionalEntityFixture.build();
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();
        var profissionalResponseDTO = ProfissionalResponseDTOFixture.build();

        when(profissionalMapper.profissionalRequestDTOToEntity(profissionalRequestDTO)).thenReturn(profissionalEntity);
        when(profissionalRepository.save(profissionalEntity)).thenReturn(profissionalEntity);
        when(profissionalMapper.profissionalEntityToResponseDTO(profissionalEntity)).thenReturn(profissionalResponseDTO);

        var retorno = profissionalService.adicionarProfissional(profissionalRequestDTO);

        verify(profissionalMapper).profissionalRequestDTOToEntity(profissionalRequestDTO);
        verify(profissionalRepository).save(profissionalEntity);
        verify(profissionalMapper).profissionalEntityToResponseDTO(profissionalEntity);

        assertEquals(profissionalResponseDTO.getId(), retorno.getId());
    }

    @Test
    void alterarProfissional(){
        var id = UUID.randomUUID().toString();
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();
        var profissionalEntity = ProfissionalEntityFixture.build();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(profissionalEntity));
        when(profissionalRepository.save(profissionalEntity)).thenReturn(profissionalEntity);

        profissionalService.alterarProfissional(id, profissionalRequestDTO);

        verify(profissionalRepository).findById(UUID.fromString(id));
        verify(profissionalRepository).save(profissionalEntity);
    }

    @Test
    void alterarContatoProfissionalNaoEncontrado(){
        var id = UUID.randomUUID().toString();
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> profissionalService.alterarProfissional(id, profissionalRequestDTO));

        verify(profissionalRepository).findById(UUID.fromString(id));

        assertEquals(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id), exception.getMessage());
    }

    @Test
    void deletarProfissional(){
        var id = UUID.randomUUID().toString();
        var profissionalEntity = ProfissionalEntityFixture.build();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(profissionalEntity));
        when(profissionalRepository.save(profissionalEntity)).thenReturn(profissionalEntity);

        profissionalService.deletarProfissional(id);

        verify(profissionalRepository).findById(UUID.fromString(id));
        verify(profissionalRepository).save(profissionalEntity);
    }

    @Test
    void deletarProfissionalNaoEncontrado(){
        var id = UUID.randomUUID().toString();

        when(profissionalRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> profissionalService.deletarProfissional(id));

        verify(profissionalRepository).findById(UUID.fromString(id));

        assertEquals(String.format(ERRO_PROFISSIONAL_NAO_ENCONTRADO, id), exception.getMessage());
    }
}
