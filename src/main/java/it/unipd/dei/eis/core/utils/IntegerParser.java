package it.unipd.dei.eis.core.utils;

public class IntegerParser {
    public static Integer tryParse(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (Exception e) {
            return null;
        }
    }
}
