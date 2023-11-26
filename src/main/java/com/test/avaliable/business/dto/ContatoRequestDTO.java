package com.test.avaliable.business.dto;

import com.test.avaliable.enumerator.TipoContato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoRequestDTO {

    @NotNull(message = "O atributo nome não pode ser nulo")
    private TipoContato nome;

    @NotBlank(message = "O atributo contato não pode ser nulo")
    private String contato;

    @NotNull(message = "O atributo id do profissional não pode ser nulo")
    private UUID idProfissional;

}
