package com.test.avaliable.business.service;

import com.test.avaliable.fixture.*;
import com.test.avaliable.infrastructure.exception.BusinessException;
import com.test.avaliable.infrastructure.exception.ResourceNotFoundException;
import com.test.avaliable.infrastructure.mappers.ContatoMapper;
import com.test.avaliable.infrastructure.repository.ContatoRepository;
import com.test.avaliable.infrastructure.repository.ProfissionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_CONTATO_NAO_ENCONTRADO;
import static com.test.avaliable.infrastructure.utils.Constantes.ERRO_PROFISSIONAL_NAO_PODE_SER_NULO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {

    @InjectMocks
    private ContatoService contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private ContatoMapper contatoMapper;

    @Test
    void buscarContato(){
        var id = UUID.randomUUID().toString();
        var contatoEntity = ContatoEntityFixture.build();
        var contatoDTO = ContatoDTOFixture.build();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contatoEntity));
        when(contatoMapper.contatoEntityToDTO(contatoEntity)).thenReturn(contatoDTO);

        var retorno = contatoService.buscarContato(id);

        verify(contatoRepository).findById(UUID.fromString(id));
        verify(contatoMapper).contatoEntityToDTO(contatoEntity);

        assertEquals(contatoDTO.getContato(), retorno.getContato());
        assertEquals(contatoDTO.getNome(), retorno.getNome());
        assertEquals(contatoDTO.getNomeProfissional(), retorno.getNomeProfissional());
        assertEquals(contatoDTO.getDataCriacao(), retorno.getDataCriacao());
    }

    @Test
    void buscarContatoNaoEncontrado(){
        var id = UUID.randomUUID().toString();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> contatoService.buscarContato(id));

        verify(contatoRepository).findById(UUID.fromString(id));

        assertEquals(String.format(ERRO_CONTATO_NAO_ENCONTRADO, id), exception.getMessage());
    }

    @Test
    void adicionarContato(){
        var profissionalEntity = ProfissionalEntityFixture.build();
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var contatoResponseDTO = ContatoResponseDTOFixture.build();
        var contatoEntity = ContatoEntityFixture.build();

        when(contatoMapper.contatoRequestDTOToEntity(contatoRequestDTO)).thenReturn(contatoEntity);
        when(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())).thenReturn(Optional.of(profissionalEntity));
        when(contatoRepository.save(contatoEntity)).thenReturn(contatoEntity);
        when(contatoMapper.contatoEntityToDTOResponse(contatoEntity)).thenReturn(contatoResponseDTO);

        var retorno = contatoService.adicionarContato(contatoRequestDTO);

        verify(contatoMapper).contatoRequestDTOToEntity(contatoRequestDTO);
        verify(profissionalRepository).findById(contatoRequestDTO.getIdProfissional());
        verify(contatoRepository).save(contatoEntity);
        verify(contatoMapper).contatoEntityToDTOResponse(contatoEntity);

        assertEquals(contatoResponseDTO.getId(), retorno.getId());
    }

    @Test
    void adicionarContatoProfissionalNaoEncontrado(){
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var contatoEntity = ContatoEntityFixture.build();

        when(contatoMapper.contatoRequestDTOToEntity(contatoRequestDTO)).thenReturn(contatoEntity);
        when(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(BusinessException.class, () -> contatoService.adicionarContato(contatoRequestDTO));

        verify(contatoMapper).contatoRequestDTOToEntity(contatoRequestDTO);
        verify(profissionalRepository).findById(contatoRequestDTO.getIdProfissional());

        assertEquals(ERRO_PROFISSIONAL_NAO_PODE_SER_NULO, exception.getMessage());
    }

    @Test
    void alterarContato(){
        var id = UUID.randomUUID().toString();
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var profissionalEntity = ProfissionalEntityFixture.build();
        var contatoEntity = ContatoEntityFixture.build();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contatoEntity));
        when(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())).thenReturn(Optional.of(profissionalEntity));
        when(contatoRepository.save(contatoEntity)).thenReturn(contatoEntity);

        contatoService.alterarContato(id, contatoRequestDTO);

        verify(contatoRepository).findById(UUID.fromString(id));
        verify(profissionalRepository).findById(contatoRequestDTO.getIdProfissional());
        verify(contatoRepository).save(contatoEntity);
    }

    @Test
    void alterarContatoProfissionalNaoEncontrado(){
        var id = UUID.randomUUID().toString();
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var contatoEntity = ContatoEntityFixture.build();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contatoEntity));
        when(profissionalRepository.findById(contatoRequestDTO.getIdProfissional())).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> contatoService.alterarContato(id, contatoRequestDTO));

        verify(contatoRepository).findById(UUID.fromString(id));
        verify(profissionalRepository).findById(contatoRequestDTO.getIdProfissional());

        assertEquals(String.format(ERRO_CONTATO_NAO_ENCONTRADO, contatoEntity.getProfissionalEntity().getId()), exception.getMessage());
    }

    @Test
    void deletarContato(){
        var id = UUID.randomUUID().toString();
        var contatoEntity = ContatoEntityFixture.build();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(contatoEntity));
        doNothing().when(contatoRepository).delete(contatoEntity);

        contatoService.deletarContato(id);

        verify(contatoRepository).findById(UUID.fromString(id));
        verify(contatoRepository).delete(contatoEntity);
    }

    @Test
    void deletarContatoNaoEncontrado(){
        var id = UUID.randomUUID().toString();

        when(contatoRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> contatoService.deletarContato(id));

        verify(contatoRepository).findById(UUID.fromString(id));

        assertEquals(String.format(ERRO_CONTATO_NAO_ENCONTRADO, id), exception.getMessage());
    }
}
