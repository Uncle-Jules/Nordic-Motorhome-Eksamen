package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Type;
import com.example.demo.models.ZipCode;
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
        // Checking if model already exists in database - if not it is inserted
        String findModelSql = "SELECT * FROM models WHERE model = ? && brand_name = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper(Motorhome.class);
        List<Motorhome> models = template.query(findModelSql, rowMapper, motorhome.getModel(), motorhome.getBrand_name());
        // If model does not exist in database it is inserted
        if(models.size() == 0){
            System.out.println("Inserting model " + motorhome.getModel());
            String insertModelSql = "INSERT INTO models VALUES (?, ?)";
            template.update(insertModelSql, motorhome.getModel(), motorhome.getBrand_name());
        }
        // Motorhome is now added to database know that we have ensured its model exists
        String sql = "INSERT INTO motorhomes VALUES (0, ?, ?, ?, ?, ?)";
        template.update(sql, motorhome.getModel(), motorhome.getType(), motorhome.getMileage(),
                motorhome.getPrice_per_day(), motorhome.getRegistration_number());
    }
    public Motorhome findById(int id){
        String sql = "SELECT * FROM motorhomes JOIN models ON models.model = motorhomes.model WHERE id = ?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper(Motorhome.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void update(int id, Motorhome motorhome){
        String sql = "UPDATE motorhomes SET mileage = ?, price_per_day = ?, type = ? WHERE id = ?";
        template.update(sql, motorhome.getMileage(), motorhome.getPrice_per_day(), motorhome.getType(), id);
    }
    public boolean delete(int id){
        String sql = "DELETE FROM motorhomes WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
