package it.unipd.dei.eis.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class for parsing dates.
 */
public class DateParser {

    /**
     * The date format tipo.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The date format.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    /**
     * Parses a date.
     *
     * @param date The date to parse
     * @return The parsed date
     */
    public static Date tryParse(String date) {
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}
