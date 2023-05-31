package it.unipd.dei.eis.core.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the SynchronizedStringFrequencyCounter class.
 */
class SynchronizedStringFrequencyCounterTest {

    /**
     * Test the add method.
     */
    @Test
    void add() {
        SynchronizedStringFrequencyCounter counter = new SynchronizedStringFrequencyCounter();
        counter.add("a");
        counter.add("a");
        counter.add("b");
        counter.add("c");
    }

    /**
     * Test the getMapSortedByValueAndKey method.
     */
    @Test
    void getMapSortedByValueAndKey() {
        SynchronizedStringFrequencyCounter counter = new SynchronizedStringFrequencyCounter();
        counter.add("a");
        counter.add("a");
        counter.add("b");
        counter.add("c");
        Map<String, Integer> map = counter.getMapSortedByValueAndKey();
        assertEquals(3, map.size());
        assertEquals(2, map.get("a"));
        assertEquals(1, map.get("b"));
        assertEquals(1, map.get("c"));
    }
}