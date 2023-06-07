package it.unipd.dei.eis.core.utils;

import it.unipd.dei.eis.core.common.Context;
import it.unipd.dei.eis.core.constants.UseCases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the ContextBuilder class.
 */
class ContextBuilderTest {

    /**
     * Test the build method.
     */
    @Test
    void build() {
        Context context = new ContextBuilder()
                .setSource("file.csv")
                .setCountArticles(1)
                .setUseCase(UseCases.DOWNLOAD)
                .build();
        assertEquals("file.csv", context.source);
    }
}