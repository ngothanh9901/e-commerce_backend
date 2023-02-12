package com.example.mediamarkbe.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_DATE_FORMAT = "yyyyMMdd";
    public static final WeekFields DEFAULT_WEEK_FIELDS = WeekFields.of(DayOfWeek.MONDAY, 4);

    public static String getDate(Instant instant) {
        if (instant == null) {
            return "";
        }
        //DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static String getDate(Instant instant, String pattern) {
        if (instant == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static String getDateTime(Instant instant) {
        if (instant == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static String getUTCDateTime(Instant instant) {
        if (instant == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }

    public static int getWeekOfYear(String date, DateTimeFormatter formatter) {
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            TemporalField woy = WeekFields.of(DEFAULT_LOCALE).weekOfWeekBasedYear();
            return localDate.get(woy);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getWeekOfYear(String date) {
        try {
            LocalDateTime localDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
            TemporalField woy = WeekFields.of(DEFAULT_LOCALE).weekOfWeekBasedYear();
            return localDate.get(woy);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getWeekOfYear(String date, String format) {
        try {
            LocalDateTime localDate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            TemporalField woy = WeekFields.of(DEFAULT_LOCALE).weekOfWeekBasedYear();
            return localDate.get(woy);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getYearWeek(String date) {
        int weekNuber = getWeekOfYear(date);
        String week = weekNuber < 10 ? "0" + weekNuber : "" + weekNuber;

        return getYear(date) + week;
    }

    public static String getYearWeek(String date, String format) {
        int weekNumber = getWeekOfYear(date, format);
        String week = weekNumber < 10 ? "0" + weekNumber : "" + weekNumber;

        return getYear(date) + week;
    }

    public static String getYearWeek(Instant instant, String pattern) {
        String date = DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault())
                .format(instant);
        return getYearWeek(date, pattern);
    }

    public static String getWeekYear(String date, String format) {
        int weekNumber = getWeekOfYear(date, format);
        String week = weekNumber < 10 ? "0" + weekNumber : "" + weekNumber;
        if (format.equalsIgnoreCase("MMM dd, yyyy")) return week + " " + date.substring(8, format.length());
        return week + ", " + getYear(date);
    }

    public static String getWeekYear(String date) {
        int weekNumber = getWeekOfYear(date, DEFAULT_DATE_FORMAT);
        String week = weekNumber < 10 ? "0" + weekNumber : "" + weekNumber;
        return week + " " + getYear(date);
    }

    public static String getYear(String date) {
        if (date == null) return "";

        if (date.length() >= 4) {
            return date.substring(0, 4);
        }
        return "";
    }

    public static String getYear(Instant date) {
        return getYear(getDate(date, DEFAULT_DATE_FORMAT));
    }


    public static String getYearMonth(String date) {
        if (date == null) return "";

        if (date.length() >= 6) {
            return date.substring(0, 6);
        }
        return "";
    }

    public static String getYearMonth(Instant instant, String pattern) {
        String date = DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault())
                .format(instant);
        return getYearMonth(date);
    }

    public static String getDay(String date) {
        if (date == null) return "";

        if (date.length() >= 8) {
            return date.substring(0, 8);
        }
        return "";
    }

    public static String getDay(Instant date) {
        return getDay(getDate(date, DEFAULT_DATE_FORMAT));
    }

    public static String getHour(String date) {
        if (date == null) return "";

        if (date.length() >= 10) {
            return date.substring(0, 10);
        }
        return "";
    }

    public static String getHour(Instant date) {
        return getHour(getDate(date, DEFAULT_DATE_FORMAT));
    }


    public static String getToday(String pattern) {
        return DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());    //"yyyyMMddHHmmss"
    }


    public static String getToday() {
        return getToday(DEFAULT_DATE_FORMAT);    //"yyyyMMddHHmmss"
    }

    public static Instant getInstant(String date, String pattern) {
        return getInstant(date, pattern, DEFAULT_LOCALE);
    }

    public static Instant getInstant(String date, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withLocale(locale)
                .withZone(ZoneId.systemDefault());

        LocalDate lastShotTime = LocalDate.parse(date, formatter);
        return lastShotTime.atStartOfDay(ZoneId.systemDefault().getRules().getOffset(Instant.now())).toInstant();
    }

    public static LocalDate getLocalDate(String pattern, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault());

        return LocalDate.parse(date, formatter);
    }


    public static LocalDate getLocalDate(String date) {
        return getLocalDate("yyyyMMdd", date);
    }

    public static String getYesterday(String pattern) {
        return DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault())
                .format(Instant.now().minus(Duration.ofDays(1)));
    }

    public static String getYesterday() {
        return getYesterday(DEFAULT_DATE_FORMAT);
    }

    public static String getPreviousDay(String pattern, Integer minusDay) {
        return DateTimeFormatter.ofPattern(pattern)
                .withLocale(DEFAULT_LOCALE)
                .withZone(ZoneId.systemDefault())
                .format(Instant.now().minus(Duration.ofDays(minusDay)));
    }

    public static String getPreviousDay(Integer minusDay) {
        return getPreviousDay(DEFAULT_DATE_FORMAT, minusDay);
    }

    public static int getLengthOfMonth(String date, String format) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            return localDate.lengthOfMonth();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getReducedTime(String dateTime, String pattern, Long reducedTime, ChronoUnit chronoUnit) {
        Instant dateTimeStamp = getInstant(dateTime, pattern);
        Instant reducedTimeStamp = dateTimeStamp.minus(reducedTime, chronoUnit);
        return getDate(reducedTimeStamp, pattern);
    }

    public static String getAddedTime(String dateTime, String pattern, Long addedTime, ChronoUnit chronoUnit) {
        Instant dateTimeStamp = getInstant(dateTime, pattern);
        Instant addedTimeStamp = dateTimeStamp.plus(addedTime, chronoUnit);
        return getDate(addedTimeStamp, pattern);
    }
    public static int getLastWeekOfYear(int year) {
        LocalDate keyDayOfNextYear = LocalDate.of(year + 1, 1, 1);
        LocalDate lastDateWeek = keyDayOfNextYear.get(DEFAULT_WEEK_FIELDS.weekOfWeekBasedYear()) == 1 ? keyDayOfNextYear.minusWeeks(1) : keyDayOfNextYear;
//		return lastDateWeek.get(WeekFields.of(DEFAULT_LOCALE).weekOfWeekBasedYear());
        return lastDateWeek.get(DEFAULT_WEEK_FIELDS.weekOfWeekBasedYear());
    }

    public static void main(String[] args) {
        System.out.println(getInstant("210101 09:30:00", "yyMMdd HH:mm:ss", Locale.getDefault()));
        System.out.println(getInstant(getDate(Instant.now(), "yyMMdd") + "09:30:00", "yyMMddHH:mm:ss"));
        System.out.println(new Date(Instant.now().toEpochMilli()));
        System.out.println(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getInstant("210218070000", "yyMMddHHmmss").toEpochMilli());
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }
}
