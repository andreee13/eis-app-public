package it.unipd.dei.eis.core.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * SynchronizedFrequencyCounter is the class used to count the frequency of an object in a thread-safe way.
 *
 * @param <T> the type of the object
 */
public class SynchronizedFrequencyCounter<T> {

    /**
     * The map field is used to store the frequency of an object.
     */
    final private Map<T, Integer> map = new HashMap<>();

    /**
     * The add method is used to add an object to the map.
     *
     * @param object the object
     */
    public synchronized void add(T object) {
        map.put(object, map.getOrDefault(object, 0) + 1);
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
