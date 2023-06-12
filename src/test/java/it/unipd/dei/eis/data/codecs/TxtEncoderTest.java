package it.unipd.dei.eis.data.codecs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the TxtEncoder class.
 */
class TxtEncoderTest {

    /**
     * Test the encode method with a Map.
     */
    @Test
    public void encode() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        String s1 = new TxtEncoder().encode(map, 2);
        String expectedString1 = "key1 1\nkey2 2\n";
        assertEquals(expectedString1, s1);
        String s2 = new TxtEncoder().encode(map);
        String expectedString2 = "key1 1\nkey2 2\nkey3 3\n";
        assertEquals(expectedString2, s2);
    }
}