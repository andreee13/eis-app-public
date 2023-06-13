package it.unipd.dei.eis.data.codecs;

import it.unipd.dei.eis.data.entities.JsonDataEntity;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the JsonDecoder class.
 */
class JsonDecoderTest {

    /**
     * Test the decode method.
     *
     * @throws Exception if an error occurs.
     */
    @Test
    public void decode() throws Exception {
        JsonDataEntity[] data = (JsonDataEntity[]) new JsonDecoder().decode(
                new String(
                        Files.readAllBytes(
                                Paths.get("src/test/resources/articles.json")
                        )
                ),
                JsonDataEntity[].class
        );
        assertTrue(data.length > 0);
    }
}
