package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ContatoRequestDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ContatoRequestDTOFixture {

    public static ContatoRequestDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ContatoRequestDTO.class);
    }
}
