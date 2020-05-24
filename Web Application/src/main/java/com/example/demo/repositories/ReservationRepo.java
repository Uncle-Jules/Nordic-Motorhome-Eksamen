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
        String sql = "INSERT INTO reservations VALUES (0, ?, ?, ?, ?, ?, ?)";
        template.update(sql, reservation.getMotorhome_id(), reservation.getCustomer_id(),
                reservation.getStart_date(), reservation.getEnd_date(), reservation.getDistance_to_pickup(), reservation.getAccessory_id());
    }

    public void update(int id, Reservation reservation){
        String sql = "UPDATE reservations SET start_date = ?, end_date = ?, distance_to_pickup = ? WHERE id = ?";
        System.out.println("Reached update in repo");
        template.update(sql, reservation.getStart_date(), reservation.getEnd_date(), reservation.getDistance_to_pickup(), id);
    }

    public boolean delete(int id){
        String sql = "DELETE FROM reservations WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
