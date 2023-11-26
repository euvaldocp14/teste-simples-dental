package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ProfissionalResponseDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ProfissionalResponseDTOFixture {

    public static ProfissionalResponseDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ProfissionalResponseDTO.class);
    }
}
