package it.unipd.dei.eis.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class for parsing dates.
 */
public abstract class DateParser {

    /**
     * The date format pattern.
     */
    public static final String PATTERN = "yyyy-MM-dd";

    /**
     * The date format object.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);

    /**
     * Parses a date.
     *
     * @param date The date to parse
     * @return The parsed date or null if the date is not valid
     */
    public static Date tryParse(String date) {
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
    public static String tryFormat(Date date) {
        try {
            return DATE_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
