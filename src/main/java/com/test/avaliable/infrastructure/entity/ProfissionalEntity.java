package com.test.avaliable.infrastructure.entity;

import com.test.avaliable.enumerator.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profissional", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfissionalEntity {

    @Id
    @NotNull(message = "Atributo ID não pode ser nulo")
    @Column(name = "id", nullable = false, columnDefinition = "uniqueidentifier")
    private UUID id;

    @NotBlank(message = "Atributo nome não pode ser nulo")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Atributo cargo não pode ser nulo")
    private Cargo cargo;

    @NotNull(message = "Atributo data nascimento não pode ser nulo")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull(message = "Atributo data de criação não pode ser nulo")
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    private Boolean ativo;


}
