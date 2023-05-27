package it.unipd.dei.eis.core.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * SynchronizedStringFrequencyCounter is the class used to count the frequency of the terms.
 */
public class SynchronizedStringFrequencyCounter {

    /**
     * The map field is used to store the terms and their frequency.
     */
    final private Map<String, Integer> map = new HashMap<>();

    /**
     * The add method is used to add a term to the map in a thread-safe way.
     *
     * @param word the term
     */
    public synchronized void add(String word) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }

    /**
     * The getMapSortedByValueAndKey method is used to sort the map by value and key.
     *
     * @return the sorted map
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public Map<String, Integer> getMapSortedByValueAndKey() {
        Map<String, Integer> map = new TreeMap<>(Comparator.comparingInt(this.map::get)
                .reversed()
                .thenComparing(Object::toString));
        map.putAll(this.map);
        return map;
    }
}