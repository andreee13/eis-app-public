package it.unipd.dei.eis.data.codecs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TxtDecoderTest {

    @Test
    public void decode() throws Exception {
        String data = "key1 1\nkey2 2\n";
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("key1", 1);
        expectedMap.put("key2", 2);
        Map<String, Integer> actualMap = new TxtDecoder().decode(data);
        assertEquals(expectedMap, actualMap);
    }
}
