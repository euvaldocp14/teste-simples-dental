package com.test.avaliable.fixture;

import com.test.avaliable.business.dto.DataAuthenticateDTO;
import io.github.benas.randombeans.EnhancedRandomBuilder;

public class DataAuthenticateDTOFixture {

    public static DataAuthenticateDTO build() {
        return EnhancedRandomBuilder
                .aNewEnhancedRandomBuilder()
                .stringLengthRange(4, 4)
                .collectionSizeRange(1, 1)
                .build()
                .nextObject(DataAuthenticateDTO.class);
    }
}
