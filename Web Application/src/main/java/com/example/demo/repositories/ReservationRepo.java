package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepo {
    @Autowired
    JdbcTemplate template;

    public List<Reservation> fetchAll(){
        String sql = "SELECT * FROM reservations JOIN motorhomes ON motorhomes.id = reservations.motorhome_id " +
                "JOIN customers ON customer_id = customers.id JOIN accessories ON reservations.id = reservations.accessory_id " +
                "JOIN models ON models.model = motorhomes.model";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper);
    }

    public Reservation findById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void add(Reservation reservation) {
        String sql = "INSERT INTO reservations VALUES (0 ,?, ?, ?, ?, ?, ?)";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        template.update(sql, rowMapper, reservation.getMotorhome_id(), reservation.getCustomer_id(),
                reservation.getStart_date(), reservation.getEnd_date(), reservation.getDistance_to_pickup(), reservation.getAccessory_id());
    }
}
