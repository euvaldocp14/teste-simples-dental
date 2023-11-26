package com.test.avaliable.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum TipoContato {

    FIXO,
    CASA,
    CELULAR,
    ESCRITORIO;

    @JsonCreator
    public static TipoContato setValue(String key) {
        return Arrays.stream(TipoContato.values())
                .filter(cargo -> cargo.toString().equals(key.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de contato inválido."));
    }

}
