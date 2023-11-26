package com.test.avaliable.infrastructure.mappers;

import com.test.avaliable.business.dto.ProfissionalDTO;
import com.test.avaliable.business.dto.ProfissionalResponseDTO;
import com.test.avaliable.fixture.ProfissionalDTOFixture;
import com.test.avaliable.fixture.ProfissionalEntityFixture;
import com.test.avaliable.fixture.ProfissionalRequestDTOFixture;
import com.test.avaliable.fixture.ProfissionalResponseDTOFixture;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfissionalMapperTest {

    @InjectMocks
    private ProfissionalMapper profissionalMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void profissionalEntityToDTO(){
        var profissionalEntity = ProfissionalEntityFixture.build();
        var profissionalDTO = ProfissionalDTOFixture.build();

        when(modelMapper.map(profissionalEntity, ProfissionalDTO.class)).thenReturn(profissionalDTO);

        var retorno = profissionalMapper.profissionalEntityToDTO(profissionalEntity);

        assertEquals(profissionalDTO.getNome(), retorno.getNome());
        assertEquals(profissionalDTO.getAtivo(), retorno.getAtivo());
        assertEquals(profissionalDTO.getDataCriacao(), retorno.getDataCriacao());
        assertEquals(profissionalDTO.getDataNascimento(), retorno.getDataNascimento());
        assertEquals(profissionalDTO.getCargo(), retorno.getCargo());
    }

    @Test
    void profissionalRequestDTOToEntity(){
        var profissionalRequestDTO = ProfissionalRequestDTOFixture.build();
        var profissionalEntity = ProfissionalEntityFixture.build();

        when(modelMapper.map(profissionalRequestDTO, ProfissionalEntity.class)).thenReturn(profissionalEntity);

        var retorno = profissionalMapper.profissionalRequestDTOToEntity(profissionalRequestDTO);

        assertEquals(profissionalEntity.getNome(), retorno.getNome());
        assertEquals(profissionalEntity.getAtivo(), retorno.getAtivo());
        assertEquals(profissionalEntity.getDataCriacao(), retorno.getDataCriacao());
        assertEquals(profissionalEntity.getDataNascimento(), retorno.getDataNascimento());
        assertEquals(profissionalEntity.getCargo(), retorno.getCargo());
    }

    @Test
    void profissionalEntityToResponseDTO(){
        var profissionalEntity = ProfissionalEntityFixture.build();
        var profissionalResponseDTO = ProfissionalResponseDTOFixture.build();

        when(modelMapper.map(profissionalEntity, ProfissionalResponseDTO.class)).thenReturn(profissionalResponseDTO);

        var retorno = profissionalMapper.profissionalEntityToResponseDTO(profissionalEntity);

        assertEquals(profissionalResponseDTO.getId(), retorno.getId());
    }

    @Test
    void profissionalEntityToDTOLista(){
        var profissionalEntity = ProfissionalEntityFixture.build();

        var retorno = profissionalMapper.profissionalEntityToDTOLista(List.of(profissionalEntity));

        assertEquals(profissionalEntity.getNome(), retorno.get(0).getNome());
        assertEquals(profissionalEntity.getAtivo(), retorno.get(0).getAtivo());
        assertEquals(profissionalEntity.getCargo(), retorno.get(0).getCargo());
        assertEquals(profissionalEntity.getDataNascimento(), retorno.get(0).getDataNascimento());
        assertEquals(profissionalEntity.getDataCriacao(), retorno.get(0).getDataCriacao());
    }
}
