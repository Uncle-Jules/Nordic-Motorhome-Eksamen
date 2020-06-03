package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateHelper {

    // Calculates how many hours there is between the two dates
    public static int hoursBetween(String startDate, String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
        LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);
        return (int) ChronoUnit.HOURS.between(formattedStartDate, formattedEndDate);
    }

    // Checks if there is a collision between the two periods. Returns true if there is
    public static boolean checkDateCollision(String startDate1, String endDate1, String startDate2, String endDate2){
        startDate1 = fixDateFormatting(startDate1);
        endDate1 = fixDateFormatting(endDate1);
        int hoursBetweenStart1End2 = hoursBetween(startDate1, endDate2);
        int hoursBetweenStart2End1 = hoursBetween(startDate2, endDate1);
        return (hoursBetweenStart1End2 >= 0 && hoursBetweenStart2End1 >= 0);
    }

    // Corrects the dateformat from the browser so that it can be saved in the database
    public static String fixDateFormatting(String date){
        return date.replace("T", " ");
    }
}
