package com.piggymade.helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

public class DateUtil {

    // Converts from ISO yyyy-MM-dd to target format using the given locale
    public static String convertFormat(String date, String targetFormat) {
        Objects.requireNonNull(date, "date");
        Objects.requireNonNull(targetFormat, "targetFormat");
        try {
            LocalDate d = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            DateTimeFormatter out = DateTimeFormatter.ofPattern(targetFormat, Locale.getDefault());
            return d.format(out);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            // Consider throwing or returning null/Optional
            return date; // preserve existing behavior
        }
    }

    // Overload: control locale explicitly
    public static String convertFormat(String date, String targetFormat, Locale locale) {
        Objects.requireNonNull(locale, "locale");
        try {
            LocalDate d = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            DateTimeFormatter out = DateTimeFormatter.ofPattern(targetFormat, locale);
            return d.format(out);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return date;
        }
    }

    public static String getTodayDate(String format) {
        Objects.requireNonNull(format, "format");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format, Locale.getDefault());
        return dtf.format(java.time.LocalDateTime.now());
    }

    public static String reformat(String date, String inPattern, String outPattern, Locale locale) {
        try {
            DateTimeFormatter in = DateTimeFormatter.ofPattern(inPattern, locale);
            DateTimeFormatter out = DateTimeFormatter.ofPattern(outPattern, locale);
            return LocalDate.parse(date, in).format(out);
        } catch (RuntimeException e) {
            return date;
        }
    }

    public static String getTodayDate(String format, ZoneId zone, Locale locale) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format, locale);
        return dtf.format(java.time.ZonedDateTime.now(zone));
    }


}
