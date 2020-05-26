package com.example.demo.repositories;

import com.example.demo.models.Accessory;
import com.example.demo.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessoryRepo {
    @Autowired
    JdbcTemplate template;

    public List<Accessory> fetchAll(){
        String sql = "SELECT * FROM accessories";
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper<>(Accessory.class);
        return template.query(sql, rowMapper);
    }

    public void add(Accessory accessory){
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper(Accessory.class);
        String sql = "INSERT INTO accessories VALUES (0, ?, ?)";
        template.update(sql, accessory.getAccessory(), accessory.getStock());
    }

    public Accessory findById(int id){
        String sql = "SELECT * FROM accessories WHERE id = ?";
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper(Accessory.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void update(int id, Accessory accessory){
        String sql = "UPDATE accessories SET stock = ? WHERE id = ?";
        template.update(sql, accessory.getStock(), id);
    }
    public boolean delete(int id){
        String sql = "DELETE FROM accessories WHERE id = ?";
        template.update(sql, id);
        return false;
    }

    public List<Accessory> getReservedAccessories(Reservation reservation) {
        int id = reservation.getId();
        String sql = "SELECT * FROM reserved_accessories JOIN accessories ON " +
                "reserved_accessories.accessory_id = accessories.id WHERE reserved_accessories.reservation_id = ?";
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper<>(Accessory.class);
        return template.query(sql, rowMapper, id);
    }
}
