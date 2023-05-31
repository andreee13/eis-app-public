package it.unipd.dei.eis.data.serialization;

import it.unipd.dei.eis.data.entities.JsonDataEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the JsonDecoder class.
 */
class JsonDecoderTest {

    /**
     * Test the decode method.
     */
    @Test
    void decode() {
        String id = "id";
        String json = String.format("{\n" +
                "    \"id\": \"%s\",\n" +
                "    \"title\": \"title\",\n" +
                "    \"body\": \"body\",\n" +
                "    \"url\": \"url\",\n" +
                "    \"date\": \"May 29, 2001 12:00:00 AM\",\n" +
                "    \"source\": \"source\"\n" +
                "  }", id);
        JsonDataEntity entity = new JsonDecoder().decode(json, JsonDataEntity.class);
        assertNotNull(entity);
        assertEquals(id, entity.id);
    }
}