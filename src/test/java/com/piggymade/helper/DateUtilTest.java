package com.piggymade.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class DateUtilTest {

    @Test
    void convertFormat_validIsoToDifferentPattern() {
        String input = "2025-08-23";
        String out = DateUtil.convertFormat(input, "dd/MM/yyyy");
        assertEquals("23/08/2025", out);
    }

    @Test
    void convertFormat_validIsoToMonthName_englishAssumption() {
        Locale defaultLocale = Locale.getDefault();
        try {
            Locale.setDefault(Locale.ENGLISH);

            String input = "2025-01-05";
            String out = DateUtil.convertFormat(input, "MMM d, yyyy");
            // SimpleDateFormat with ENGLISH would yield "Jan 5, 2025"
            assertEquals("Jan 5, 2025", out);
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    void convertFormat_invalidDate_returnsOriginal() {
        String input = "2025/08/23"; // wrong delimiter
        String out = DateUtil.convertFormat(input, "yyyyMMdd");
        assertEquals(input, out);
    }

    @Test
    void convertFormat_invalidNumbers_returnsOriginal() {
        String input = "2025-13-40"; // invalid month/day
        String out = DateUtil.convertFormat(input, "yyyyMMdd");
        assertEquals(input, out);
    }

    @Test
    void convertFormat_leapDay_valid() {
        String input = "2024-02-29";
        String out = DateUtil.convertFormat(input, "yyyyMMdd");
        assertEquals("20240229", out);
    }

    @Test
    void convertFormat_minimalPattern() {
        String input = "1999-12-01";
        String out = DateUtil.convertFormat(input, "yyyy");
        assertEquals("1999", out);
    }

    @Test
    void getTodayDate_basicPattern_matchesSystemNowYearMonthDay() {
        // Use a fixed pattern and compare components to LocalDate.now()
        String pattern = "yyyy-MM-dd";
        String today = DateUtil.getTodayDate(pattern);

        LocalDate parsed = LocalDate.parse(today, DateTimeFormatter.ofPattern(pattern));
        LocalDate now = LocalDate.now();

        // Same calendar day in system default zone
        assertEquals(now, parsed);
    }

    @Test
    void getTodayDate_textualMonth_respectsLocale() {
        Locale defaultLocale = Locale.getDefault();
        try {
            Locale.setDefault(Locale.ENGLISH);

            String pattern = "MMM yyyy";
            String today = DateUtil.getTodayDate(pattern);
            // Should parse using ENGLISH month abbreviations
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
            assertDoesNotThrow(() -> LocalDate.parse("01 " + today, DateTimeFormatter.ofPattern("dd " + pattern, Locale.ENGLISH)));
        } finally {
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    void getTodayDate_invalidPattern_throws() {
        assertThrows(IllegalArgumentException.class, () -> DateUtil.getTodayDate("invalid-pattern-#"));
    }
}
