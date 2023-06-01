package it.unipd.dei.eis.data.serialization;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Encodes an object in a txt format.
 */
public class TxtEncoder implements IEncoder {

    /**
     * Encodes the object in a txt format.
     *
     * @param object the object to encode
     * @param args   optional arguments
     * @return the encoded object
     */
    @Override
    public <T> String encode(T object, Object... args) {
        if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) object;
            List<?> keys = map
                    .keySet()
                    .stream()
                    .limit(args.length > 0 ? (int) args[0] : map.size())
                    .collect(Collectors.toList());
            StringBuilder stringBuilder = new StringBuilder();
            for (Object key : keys) {
                stringBuilder.append(key)
                        .append(' ')
                        .append(map.get(key))
                        .append('\n');
            }
            return stringBuilder.toString();
        }
        return object.toString();
    }
}
