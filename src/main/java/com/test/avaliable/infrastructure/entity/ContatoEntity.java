package com.test.avaliable.infrastructure.entity;

import com.test.avaliable.enumerator.TipoContato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contato", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class ContatoEntity {

    @Id
    @NotNull(message = "Atributo ID não pode ser nulo")
    @Column(name = "id", nullable = false, columnDefinition = "uniqueidentifier")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Atributo nome não pode ser nulo")
    private TipoContato nome;

    @NotBlank(message = "Atributo contato não pode ser nulo")
    private String contato;

    @NotNull(message = "Atributo data de criação não pode ser nulo")
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @NotNull(message = "Atributo profissional não pode ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id")
    private ProfissionalEntity profissionalEntity;

}
