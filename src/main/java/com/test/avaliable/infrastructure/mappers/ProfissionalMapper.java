package com.test.avaliable.infrastructure.mappers;

import com.test.avaliable.business.dto.ProfissionalDTO;
import com.test.avaliable.business.dto.ProfissionalRequestDTO;
import com.test.avaliable.business.dto.ProfissionalResponseDTO;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfissionalMapper {

    private final ModelMapper modelMapper;

    public ProfissionalDTO profissionalEntityToDTO(ProfissionalEntity profissionalEntity) {
        return modelMapper.map(profissionalEntity, ProfissionalDTO.class);
    }

    public ProfissionalEntity profissionalRequestDTOToEntity(ProfissionalRequestDTO profissionalRequestDTO) {
        return modelMapper.map(profissionalRequestDTO, ProfissionalEntity.class);
    }

    public ProfissionalResponseDTO profissionalEntityToResponseDTO(ProfissionalEntity profissionalEntity) {
        return modelMapper.map(profissionalEntity, ProfissionalResponseDTO.class);
    }

    public List<ProfissionalDTO> profissionalEntityToDTOLista(List<ProfissionalEntity> profissionalEntity) {
        return profissionalEntity.stream().map(ProfissionalDTO::new).collect(Collectors.toList());
    }

}
