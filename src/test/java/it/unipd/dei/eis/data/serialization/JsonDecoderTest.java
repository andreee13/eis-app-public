package it.unipd.dei.eis.data.serialization;

import it.unipd.dei.eis.data.entities.JsonDataEntity;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the JsonDecoder class.
 */
class JsonDecoderTest {

    /**
     * Test the decode method.
     */
    @Test
    public void decode() throws Exception{
        List<JsonDataEntity> data = Arrays.asList( new JsonDecoder().decode(new String(Files.readAllBytes(Paths.get("articles.json"))), JsonDataEntity[].class));
        assertTrue(data.size() > 0);
    }
}
