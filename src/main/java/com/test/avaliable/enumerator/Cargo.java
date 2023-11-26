package com.test.avaliable.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Cargo {

    DESENVOLVEDOR,
    DESIGNER,
    SUPORTE,
    TESTER;

    @JsonCreator
    public static Cargo setValue(String key) {
        return Arrays.stream(Cargo.values())
                .filter(cargo -> cargo.toString().equals(key.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cargo inválido."));
    }

}
