package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import com.example.demo.services.MotorhomeService;
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

    public List<Reservation> fetchAll() {
        String sql = "SELECT * FROM reservations JOIN motorhomes ON motorhomes.id = reservations.motorhome_id " +
                "JOIN customers ON reservations.customer_id = customers.id JOIN models ON models.model = motorhomes.model";
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
        String start_date = reservation.getStart_date();
        String end_date = reservation.getEnd_date();
        String season = calculateSeason(reservation.getStart_date());
        int distance_to_pickup = reservation.getDistance_to_pickup();
        double price_per_day = motorhomeService.findById(reservation.getMotorhome_id()).getPrice_per_day();
        template.update(sql, reservation.getMotorhome_id(), reservation.getCustomer_id(),
                start_date, end_date, distance_to_pickup,
                reservation.getAccessory_id(), season, calculateTotalPrice(price_per_day,distance_to_pickup, season, start_date, end_date));
    }

    public void update(int id, Reservation reservation) {
        String sql = "UPDATE reservations SET start_date = ?, end_date = ?, distance_to_pickup = ? WHERE id = ?";
        System.out.println("Reached update in repo");
        template.update(sql, reservation.getStart_date(), reservation.getEnd_date(), reservation.getDistance_to_pickup(), id);
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        template.update(sql, id);
        return false;
    }

    private double calculateTotalPrice(double price_per_day, int distance_to_pickup, String season, String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formattedStartDate = LocalDateTime.parse(start_date, formatter);
        LocalDateTime formattedEndDate = LocalDateTime.parse(end_date, formatter);
        int numberOfDays = (int) ChronoUnit.DAYS.between(formattedStartDate, formattedEndDate);
        double basePrice = price_per_day * numberOfDays + distance_to_pickup;
        double distancePrice = distance_to_pickup * 0.7;
        switch (season){
            case "vinter":
                return basePrice;
            case "forår":
            case "efterår":
                return basePrice * 1.3;
            case "sommer":
                return basePrice * 1.6;
        }
        return -1;
    }

    private String calculateSeason(String date) {
        int month = Integer.parseInt(date.substring(5, 7));
        switch(month) {
            case 12:
            case 1:
            case 2:
                return "vinter";
            case 3:
            case 4:
            case 5:
                return "forår";
            case 6:
            case 7:
            case 8:
                return "sommer";
            case 9:
            case 10:
            case 11:
                return "efterår";
            default:
                break;
        }
        return "undefined";
    }

}






