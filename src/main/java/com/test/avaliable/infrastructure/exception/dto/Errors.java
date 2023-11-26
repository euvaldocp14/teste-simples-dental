package com.test.avaliable.infrastructure.exception.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Errors {

    @NotEmpty
    private List<ErrorDetails> errors;

    private Meta meta;
}
