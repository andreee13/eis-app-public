package it.unipd.dei.eis.core.utils;

/**
 * A utility class for parsing integers.
 */
public abstract class IntegerParser {

    /**
     * Parses an integer from a string.
     *
     * @param integer the string to parse
     * @return the integer parsed or null if the string is not a valid integer
     */
    public static Integer tryParse(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (Exception e) {
            return null;
        }
    }
}
