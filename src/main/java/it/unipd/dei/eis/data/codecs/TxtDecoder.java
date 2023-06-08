package it.unipd.dei.eis.data.codecs;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TxtDecoder implements IDecoder<Map<String, Integer>> {


    /**
     * Decodes the data.
     *
     * @param data the data to decode
     * @param args optional arguments
     * @return the decoded data
     */
    @Override
    public Map<String, Integer> decode(String data, Object... args) {
        return Arrays.stream(data.split("\n"))
                .map(line -> line.split(" ")).
                collect(Collectors.toMap(line -> line[0], line -> Integer.parseInt(line[1])));
    }
}
