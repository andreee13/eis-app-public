package it.unipd.dei.eis.data.entities;

import it.unipd.dei.eis.core.utils.DateParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the JsonDataEntity class.
 */
class JsonDataEntityTest {

    /**
     * A sample JSON data entity.
     */
    private final JsonDataEntity entity = new JsonDataEntity(
            1,
            "title",
            "body",
            "url",
            DateParser.tryParse("2023-01-01"),
            "source"
    );

    /**
     * Test the contains method.
     */
    @Test
    void contains() {
        assertTrue(entity.contains("title"));
        assertTrue(entity.contains("body"));
    }

    /**
     * Test the before method.
     */
    @Test
    void before() {
        assertTrue(entity.before(DateParser.tryParse("2023-01-02")));
    }

    /**
     * Test the after method.
     */
    @Test
    void after() {
        assertTrue(entity.after(DateParser.tryParse("2022-12-31")));
    }
}
