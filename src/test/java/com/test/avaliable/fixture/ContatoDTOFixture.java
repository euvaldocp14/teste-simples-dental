package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ContatoDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ContatoDTOFixture {

    public static ContatoDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ContatoDTO.class);
    }
}
