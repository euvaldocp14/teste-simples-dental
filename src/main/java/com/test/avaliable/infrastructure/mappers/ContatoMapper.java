package com.test.avaliable.infrastructure.mappers;

import com.test.avaliable.business.dto.ContatoDTO;
import com.test.avaliable.business.dto.ContatoRequestDTO;
import com.test.avaliable.business.dto.ContatoResponseDTO;
import com.test.avaliable.infrastructure.entity.ContatoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContatoMapper {

    private final ModelMapper modelMapper;

    public ContatoDTO contatoEntityToDTO(ContatoEntity contatoEntity){
        return modelMapper.map(contatoEntity, ContatoDTO.class);
    }

    public ContatoResponseDTO contatoEntityToDTOResponse(ContatoEntity contatoEntity){
        return modelMapper.map(contatoEntity, ContatoResponseDTO.class);
    }

    public ContatoEntity contatoRequestDTOToEntity(ContatoRequestDTO contatoRequestDTO){
        return modelMapper.map(contatoRequestDTO, ContatoEntity.class);
    }

    public List<ContatoDTO> contatoEntityToDTOLista(List<ContatoEntity> contatoEntityList) {
        return contatoEntityList.stream().map(ContatoDTO::new).collect(Collectors.toList());
    }
}
