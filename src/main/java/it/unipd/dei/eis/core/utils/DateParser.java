package it.unipd.dei.eis.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static final String DATE_FORMAT = "yyyy-MM-dd'";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static Date parse(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
