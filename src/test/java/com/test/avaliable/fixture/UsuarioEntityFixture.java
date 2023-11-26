package com.test.avaliable.fixture;

import com.test.avaliable.infrastructure.entity.UsuarioEntity;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class UsuarioEntityFixture {

    public static UsuarioEntity build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(UsuarioEntity.class);
    }

    public static UsuarioEntity buildNomeUsuario(String login) {
        UsuarioEntity.UsuarioEntityBuilder objeto = EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(UsuarioEntity.UsuarioEntityBuilder.class);

        objeto.login(login);

        return objeto.build();
    }
}
