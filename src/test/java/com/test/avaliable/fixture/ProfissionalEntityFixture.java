package com.test.avaliable.fixture;

import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ProfissionalEntityFixture {

    public static ProfissionalEntity build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ProfissionalEntity.class);
    }
}
