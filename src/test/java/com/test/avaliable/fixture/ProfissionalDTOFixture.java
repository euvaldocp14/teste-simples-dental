package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.ProfissionalDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class ProfissionalDTOFixture {

    public static ProfissionalDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(ProfissionalDTO.class);
    }
}
