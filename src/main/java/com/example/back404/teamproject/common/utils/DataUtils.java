package com.example.back404.teamproject.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime dateTime){
        return (dateTime != null) ? dateTime.format(FORMATTER) : null;
    }

    public static LocalDateTime parse(String dateString) {
        return (dateString != null && !dateString.isEmpty()) ? LocalDateTime.parse(dateString, FORMATTER) : null;
    }

    public static String nowFormated() { return format(LocalDateTime.now());}
}

