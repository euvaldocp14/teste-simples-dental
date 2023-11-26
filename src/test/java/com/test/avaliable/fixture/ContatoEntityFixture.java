package com.test.avaliable.fixture;

import com.test.avaliable.infrastructure.entity.ContatoEntity;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ContatoEntityFixture {

    public static ContatoEntity build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ContatoEntity.class);
    }
}
