package com.test.avaliable.infrastructure.exception.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flywaydb.core.api.ErrorDetails;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta {

    @NotNull
    private Integer totalRecords;

    @NotNull
    private Integer totalPages;
}
