package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Motorhome> fetchAll(){
        String sql = "SELECT *, brand_name as brand FROM motorhomes JOIN models ON motorhomes.model = models.model";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }
    public void add(Motorhome motorhome){
        String sql = "INSERT INTO motorhomes VALUES (0, ?, ?, ?, ?, ?)";
        template.update(sql, motorhome.getType(), motorhome.getModel(), motorhome.getMileage(),
                motorhome.getPrice_per_day(), motorhome.getRegistration_number());
    }
    public Motorhome findById(int id){
        String sql = "SELECT * FROM motorhomes WHERE id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper(Motorhome.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void update(int id, Motorhome motorhome){
        String sql = "UPDATE motorhomes SET mileage = ? WHERE id = ?";
        template.update(sql, motorhome.getMileage(), id);
    }
    public boolean delete(int id){
        String sql = "DELETE FROM motorhomes WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
