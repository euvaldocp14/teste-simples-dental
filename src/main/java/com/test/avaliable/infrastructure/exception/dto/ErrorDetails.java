package com.test.avaliable.infrastructure.exception.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {

    @Max(value = 255)
    @Pattern(regexp = "[\\w\\W\\s]*")
    @NotBlank
    private String code;

    @Max(value = 255)
    @Pattern(regexp = "[\\w\\W\\s]*")
    @NotBlank
    private String title;

    @Max(value = 255)
    @Pattern(regexp = "[\\w\\W\\s]*")
    @NotBlank
    private String detail;

}
