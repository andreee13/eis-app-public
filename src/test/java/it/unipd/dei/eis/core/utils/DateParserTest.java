package it.unipd.dei.eis.core.utils;

import org.junit.jupiter.api.Test;

class DateParserTest {

    @Test
    void tryParse() {
        DateParser.tryParse("2023-01-01");
    }

    @Test
    void tryFormat() {
        DateParser.tryFormat(DateParser.tryParse("2023-01-01"));
    }
}