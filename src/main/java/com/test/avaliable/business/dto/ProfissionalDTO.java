package com.test.avaliable.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.avaliable.enumerator.Cargo;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProfissionalDTO {

    private String nome;
    private Cargo cargo;
    private LocalDate dataNascimento;
    private LocalDate dataCriacao;
    private Boolean ativo;

    public ProfissionalDTO(ProfissionalEntity profissionalEntity) {
        this.nome = profissionalEntity.getNome();
        this.cargo = profissionalEntity.getCargo();
        this.dataNascimento = profissionalEntity.getDataNascimento();
        this.dataCriacao = profissionalEntity.getDataCriacao();
        this.ativo = profissionalEntity.getAtivo();
    }


}
