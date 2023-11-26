package com.test.avaliable.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pessoa", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaEntity {

    @Id
    @NotNull(message = "Atributo ID não pode ser nulo")
    @Column(name = "id", nullable = false, columnDefinition = "uniqueidentifier")
    private UUID id;

    @NotBlank(message = "Atributo nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Atributo data de nascimento não pode ser nulo")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String genero;


}
