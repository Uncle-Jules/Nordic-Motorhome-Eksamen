package com.example.demo.utils;

import com.example.demo.models.Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateHelper {

    public static int daysBetween(String startDate, String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
        LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);
        return (int) ChronoUnit.HOURS.between(formattedStartDate, formattedEndDate);
    }

    public static boolean checkDateCollission(String startDate1, String endDate1, String startDate2, String endDate2){
        startDate1 = Reservation.fixDateFormatting(startDate1);
        startDate2 = Reservation.fixDateFormatting(startDate2);
        endDate1 = Reservation.fixDateFormatting(endDate1);
        endDate2 = Reservation.fixDateFormatting(endDate2);
        int daysBetweenStart1End2 = daysBetween(startDate1, endDate2);
        System.out.println("Days between start 1 and end 2: " + daysBetweenStart1End2);
        int daysBetweenStart2End1 = daysBetween(startDate2, endDate1);
        System.out.println("Days between start 2 end 1: " + daysBetweenStart2End1);
        return (daysBetweenStart1End2 >= 0 && daysBetweenStart2End1 >= 0);
        //( start1 <= end2 and start2 <= end1 )
    }
}
