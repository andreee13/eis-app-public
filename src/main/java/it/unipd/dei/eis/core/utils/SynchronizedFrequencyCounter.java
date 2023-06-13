package it.unipd.dei.eis.core.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SynchronizedFrequencyCounter is the class used to count the frequency of an object in a thread-safe way.
 *
 * @param <T> the type of the object
 */
public class SynchronizedFrequencyCounter<T> {

    /**
     * The map field is used to store the frequency of an object.
     */
    final private Map<T, Integer> map = new ConcurrentHashMap<>();

    /**
     * The add method is used to add an object to the map.
     *
     * @param object the object
     */
    public void add(T object) {
        map.merge(object, 1, Integer::sum);
    }

    /**
     * The getMapSortedByValueAndKey method is used to sort the map by value and key.
     *
     * @return the sorted map
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public Map<T, Integer> getMapSortedByValueAndKey() {
        Map<T, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get)
                .reversed()
                .thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }
}
