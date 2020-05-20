package com.example.demo.repositories;

import com.example.demo.models.Motorhome;
import com.example.demo.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Type> fetchAll(){
        String sql = "SELECT * FROM types";
        RowMapper<Type> rowMapper = new BeanPropertyRowMapper<>(Type.class);
        return template.query(sql, rowMapper);
    }

    public void add(Type type){
        String sql = "INSERT INTO types VALUES (?, ?)";
        template.update(sql, type.getType(), type.getBeds());
    }

    public Type findById(String type){
        String sql = "SELECT * FROM types WHERE type = ?";
        RowMapper<Type> rowMapper = new BeanPropertyRowMapper(Type.class);
        return template.queryForObject(sql, rowMapper, type);
    }

    public void update(String type, Type typeObject){
        String sql = "UPDATE types SET beds = ? WHERE type = ?";
        template.update(sql, typeObject.getBeds(), type);
    }
    public boolean delete(String type){
        String sql = "DELETE FROM types WHERE type = ?";
        template.update(sql, type);
        return false;
    }

}
