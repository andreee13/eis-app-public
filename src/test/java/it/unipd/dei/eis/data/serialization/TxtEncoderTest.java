package it.unipd.dei.eis.data.serialization;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the TxtEncoder class.
 */
class TxtEncoderTest {

    /**
     * Test the encode method with a String.
     */
    @Test
    public void encodeObject() {
        String s = new TxtEncoder().encode("Trying to encode a string..", 1);

        assertEquals("Trying to encode a string..", s);
    }

    /**
     * Test the encode method with a Map.
     */
    @Test
    public void encodeMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String s = new TxtEncoder().encode(map, 2);

        String expectedString = "key1 value1\nkey2 value2\n";
        assertEquals(expectedString, s);
    }
}