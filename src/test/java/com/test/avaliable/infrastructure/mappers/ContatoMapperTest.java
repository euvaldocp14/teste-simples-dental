package com.test.avaliable.infrastructure.mappers;

import com.test.avaliable.business.dto.ContatoDTO;
import com.test.avaliable.business.dto.ContatoResponseDTO;
import com.test.avaliable.fixture.ContatoDTOFixture;
import com.test.avaliable.fixture.ContatoEntityFixture;
import com.test.avaliable.fixture.ContatoRequestDTOFixture;
import com.test.avaliable.fixture.ContatoResponseDTOFixture;
import com.test.avaliable.infrastructure.entity.ContatoEntity;
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
public class ContatoMapperTest {

    @InjectMocks
    private ContatoMapper contatoMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void contatoEntityToDTO(){
        var contatoEntity = ContatoEntityFixture.build();
        var contatoDTO = ContatoDTOFixture.build();

        when(modelMapper.map(contatoEntity, ContatoDTO.class)).thenReturn(contatoDTO);

        var retorno = contatoMapper.contatoEntityToDTO(contatoEntity);

        assertEquals(contatoDTO.getContato(), retorno.getContato());
        assertEquals(contatoDTO.getNome(), retorno.getNome());
        assertEquals(contatoDTO.getDataCriacao(), retorno.getDataCriacao());
        assertEquals(contatoDTO.getNomeProfissional(), retorno.getNomeProfissional());
    }

    @Test
    void contatoEntityToDTOResponse(){
        var contatoEntity = ContatoEntityFixture.build();
        var contatoResponseDTO = ContatoResponseDTOFixture.build();

        when(modelMapper.map(contatoEntity, ContatoResponseDTO.class)).thenReturn(contatoResponseDTO);

        var retorno = contatoMapper.contatoEntityToDTOResponse(contatoEntity);

        assertEquals(contatoResponseDTO.getId(), retorno.getId());
    }

    @Test
    void contatoRequestDTOToEntity(){
        var contatoRequestDTO = ContatoRequestDTOFixture.build();
        var contatoEntity = ContatoEntityFixture.build();

        when(modelMapper.map(contatoRequestDTO, ContatoEntity.class)).thenReturn(contatoEntity);

        var retorno = contatoMapper.contatoRequestDTOToEntity(contatoRequestDTO);

        assertEquals(contatoEntity.getContato(), retorno.getContato());
        assertEquals(contatoEntity.getNome(), retorno.getNome());
    }

    @Test
    void contatoEntityToDTOLista(){
        var contatoEntity = ContatoEntityFixture.build();

        var retorno = contatoMapper.contatoEntityToDTOLista(List.of(contatoEntity));

        assertEquals(contatoEntity.getContato(), retorno.get(0).getContato());
        assertEquals(contatoEntity.getNome(), retorno.get(0).getNome());
        assertEquals(contatoEntity.getDataCriacao(), retorno.get(0).getDataCriacao());
        assertEquals(contatoEntity.getProfissionalEntity().getNome(), retorno.get(0).getNomeProfissional());
    }
}
