package it.unipd.dei.eis.data.codecs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Encodes an object in a txt format.
 */
public class TxtEncoder implements IEncoder<Map<String, Integer>> {

    /**
     * Encodes the object in a txt format.
     *
     * @param object the object to encode
     * @param args   optional arguments
     * @return the encoded object
     */
    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public String encode(Map<String, Integer> object, Object... args) {
        List<?> keys = object
                .keySet()
                .parallelStream()
                .limit(args.length > 0 ? (int) args[0] : object.size())
                .collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (Object key : keys) {
            stringBuilder.append(key)
                    .append(' ')
                    .append(object.get(key))
                    .append('\n');
        }
        return stringBuilder.toString();
    }
}
