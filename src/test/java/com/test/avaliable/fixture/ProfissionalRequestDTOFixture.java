package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ProfissionalRequestDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ProfissionalRequestDTOFixture {

    public static ProfissionalRequestDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ProfissionalRequestDTO.class);
    }
}
