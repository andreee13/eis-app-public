package it.unipd.dei.eis.data.serialization;

import it.unipd.dei.eis.core.utils.DateParser;
import it.unipd.dei.eis.domain.models.ArticleModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the JsonEncoder class.
 */
class JsonEncoderTest {

    /**
     * Test the encode method.
     */
    @Test
    void encode() {
        String s = new JsonEncoder().encode(new ArticleModel(
                "title",
                "body",
                "url",
                DateParser.tryParse("2023-01-01"),
                "source"
        ));
        assertNotNull(s);
    }
}