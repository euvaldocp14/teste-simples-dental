package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ContatoResponseDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ContatoResponseDTOFixture {

    public static ContatoResponseDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ContatoResponseDTO.class);
    }
}
