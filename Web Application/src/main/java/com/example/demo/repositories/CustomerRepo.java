package com.example.demo.repositories;

import com.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> fetchAll(){
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
    public void add(Customer customer){
        String sql = "INSERT INTO customers VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(customer.getAddress_id());
        template.update(sql, customer.getFirst_name(), customer.getLast_name(), customer.getPhone_number(), customer.getAddress_id(), customer.getBirth_date(), customer.getPayment_details(), customer.getDrivers_license());
    }
    public Customer findById(int id){
        String sql = "SELECT * FROM customers WHERE id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper(Customer.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void update(int id, Customer customer){
        String sql = "UPDATE customers SET payment_details = ? WHERE id = ?";
            template.update(sql, customer.getPayment_details(), id);
    }
    public boolean delete(int id){
        String sql = "DELETE FROM customers WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
