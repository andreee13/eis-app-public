package it.unipd.dei.eis.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility interface for parsing dates.
 */
public interface DateParser {

    /**
     * The date format pattern.
     */
    String PATTERN = "yyyy-MM-dd";

    /**
     * The date format object.
     */
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);

    /**
     * Parses a date.
     *
     * @param date The date to parse
     * @return The parsed date or null if the date is not valid
     */
    static Date tryParse(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats a date.
     *
     * @param date The date to format
     * @return The formatted date or null if the date is not valid
     */
    static String tryFormat(Date date) {
        try {
            return DATE_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
