package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateHelper {

    public static int hoursBetween(String startDate, String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
        LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);
        return (int) ChronoUnit.HOURS.between(formattedStartDate, formattedEndDate);
    }

    public static boolean checkDateCollission(String startDate1, String endDate1, String startDate2, String endDate2){
        startDate1 = fixDateFormatting(startDate1);
        startDate2 = fixDateFormatting(startDate2);
        endDate1 = fixDateFormatting(endDate1);
        endDate2 = fixDateFormatting(endDate2);
        int daysBetweenStart1End2 = hoursBetween(startDate1, endDate2);
        int daysBetweenStart2End1 = hoursBetween(startDate2, endDate1);
        return (daysBetweenStart1End2 >= 0 && daysBetweenStart2End1 >= 0);
    }

    public static String fixDateFormatting(String date){
        return date = date.replace("T", " ");
    }
}
