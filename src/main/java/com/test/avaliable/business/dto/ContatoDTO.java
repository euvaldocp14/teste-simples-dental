package com.test.avaliable.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.avaliable.enumerator.TipoContato;
import com.test.avaliable.infrastructure.entity.ContatoEntity;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ContatoDTO {

    private TipoContato nome;
    private String contato;
    private LocalDate dataCriacao;
    private String nomeProfissional;

    public ContatoDTO(ContatoEntity contatoEntity) {
        this.nome = contatoEntity.getNome();
        this.contato = contatoEntity.getContato();
        this.dataCriacao = contatoEntity.getDataCriacao();
        this.nomeProfissional = contatoEntity.getProfissionalEntity().getNome();
    }

}
