package com.test.avaliable.infrastructure.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorFields {

    private String campo;

    private String mensagem;
}
