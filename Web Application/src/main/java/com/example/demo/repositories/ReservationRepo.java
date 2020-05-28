package com.example.demo.repositories;

import com.example.demo.AppConfig;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import com.example.demo.services.MotorhomeService;
import com.example.demo.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class ReservationRepo {
    @Autowired
    JdbcTemplate template;
    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    AppConfig appConfig;

    public List<Reservation> fetchAll() {
        String sql = "SELECT *, reservations.id as id FROM reservations JOIN motorhomes ON motorhomes.id = reservations.motorhome_id " +
                "JOIN customers ON customer_id = customers.id JOIN models ON models.model = motorhomes.model";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper);
    }

    public Reservation findById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void add(Reservation reservation) {
        String sql = "INSERT INTO reservations VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?)";
        String start_date = Reservation.fixDateFormatting(reservation.getStart_date());
        String end_date = Reservation.fixDateFormatting(reservation.getEnd_date());
        String season = calculateSeason(reservation.getStart_date());
        int distance_to_pickup = reservation.getDistance_to_pickup();
        double price_per_day = motorhomeService.findById(reservation.getMotorhome_id()).getPrice_per_day();
        double totalPrice = calculateTotalPrice(price_per_day,distance_to_pickup, season, start_date, end_date);

        template.update(sql, reservation.getMotorhome_id(), reservation.getCustomer_id(),
                reservation.getPayment_details(), start_date, end_date, distance_to_pickup, season, totalPrice);
    }

    public void update(int id, Reservation reservation) {
        String sql = "UPDATE reservations SET start_date = ?, end_date = ?, distance_to_pickup = ? WHERE id = ?";
        String start_date = Reservation.fixDateFormatting(reservation.getStart_date());
        String end_date = Reservation.fixDateFormatting(reservation.getEnd_date());
        template.update(sql, start_date, end_date, reservation.getDistance_to_pickup(), id);
    }

    public void addAccessory(int reservationId, int accessoryId){
        System.out.println("Repo reservation ID: " + reservationId);
        System.out.println("Repo accessory ID: " + accessoryId);
        String addAccessoryIDSql = "INSERT INTO reserved_accessories VALUES (?, ?)";
        template.update(addAccessoryIDSql, reservationId, accessoryId);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        template.update(sql, id);
        return false;
    }

    private String calculateSeason(String date) {
        int month = Integer.parseInt(date.substring(5, 7));
        switch(month) {
            case 12:
            case 1:
            case 2:
                return "Lavsæson";
            case 3:
            case 4:
            case 5:
            case 9:
            case 10:
            case 11:
                return "Mellemsæson";
            case 6:
            case 7:
            case 8:
                return "Højsæson";
            default:
                break;
        }
        return "undefined";
    }

    private double calculateTotalPrice(double price_per_day, int distance_to_pickup, String season, String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedStartDate = LocalDateTime.parse(start_date, formatter);
        LocalDateTime formattedEndDate = LocalDateTime.parse(end_date, formatter);
        int numberOfDays = (int) ChronoUnit.DAYS.between(formattedStartDate, formattedEndDate);
        double basePrice = price_per_day * numberOfDays;
        double pickupDropoffTax = appConfig.getPickupDropoffTax();
        double middleSeasonPercent = appConfig.getMiddleSeasonPercent();
        double highSeasonPercent = appConfig.getHighSeasonPercent();
        double distancePrice = distance_to_pickup * pickupDropoffTax;
        switch (season){
            case "Lavsæson":
                return basePrice + distancePrice;
            case "Mellemsæson":
                return basePrice * middleSeasonPercent + distancePrice;
            case "Højsæson":
                return basePrice * highSeasonPercent + distancePrice;
        }
        return -1;
    }

    public boolean checkIfReserved(Reservation reservation){
        int motorhomeId = reservation.getMotorhome_id();
        String sql = "SELECT * FROM reservations WHERE motorhome_id = ?";
        RowMapper rowMapper = new BeanPropertyRowMapper(Reservation.class);
        List<Reservation> reservations = template.query(sql, rowMapper, motorhomeId);
        // If motorhome is not present in any other reservation,
        // there is no possibility of a double reservation of the same motorhome
        if (reservations.size() == 0){
            return false;
        }
        boolean isReserved = false;
        for(Reservation res : reservations){
            isReserved = DateHelper.checkDateCollission(reservation.getStart_date(), reservation.getEnd_date(),
                         res.getStart_date(), res.getEnd_date());
            if(isReserved){
                return true;
            }
        }
        return isReserved;
    }
}






