package it.unipd.dei.eis.data.codecs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the TxtDecoder class.
 */
public class TxtDecoderTest {

    /**
     * Test the decode method.
     *
     * @throws IOException if the file cannot be read
     */
    @Test
    public void decode() throws IOException {
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("energy", 10);
        expectedMap.put("nuclear", 10);
        expectedMap.put("gas", 5);
        Map<String, Integer> actualMap = new TxtDecoder().decode(
                Files.readString(
                        Paths.get("src/test/resources/terms.txt")
                )
        );
        assertEquals(expectedMap, actualMap);
    }
}
