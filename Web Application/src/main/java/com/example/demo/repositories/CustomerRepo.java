package com.example.demo.repositories;

import com.example.demo.models.Address;
import com.example.demo.models.Customer;
import com.example.demo.models.ZipCode;
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
    public void add(Customer customer, Address address, ZipCode zipCode){
        // Checking if the zip code already exists in database
        String checkZipSql = "SELECT * FROM zip_codes WHERE zip = ?";
        RowMapper<ZipCode> rowMapper = new BeanPropertyRowMapper<>(ZipCode.class);
        List<ZipCode> listZip = template.query(checkZipSql, rowMapper, zipCode.getZip());
        // If zip code doesn't exist the zip is added
        if(listZip.size() == 0) {
            String insertZip = "INSERT INTO zip_codes VALUES (?, ?, ?)";
            template.update(insertZip, zipCode.getZip(), zipCode.getCity(), zipCode.getCountry());
        }

        // Checking if exact address already exists in database
        String checkAddressSql = "SELECT id FROM addresses WHERE street_name = ? AND street_number = ? AND apartment_number = ?";
        RowMapper<Address> addressRowMapper = new BeanPropertyRowMapper<>(Address.class);
        List<Address> listAddress = template.query(checkAddressSql, addressRowMapper, address.getStreet_name(), address.getStreet_number(), address.getApartment_number());
        // If address does not exist in database it is added
        if(listAddress.size() == 0) {
            String insertAddress = "INSERT INTO addresses VALUES (0, ?, ?, ?, ?)";
            template.update(insertAddress, address.getStreet_name(), address.getStreet_number(), address.getApartment_number(), zipCode.getZip());
        }
        // Getting ID of address (whether it already existed or not)
        int id = template.query(checkAddressSql, addressRowMapper, address.getStreet_name(), address.getStreet_number(), address.getApartment_number()).get(0).getId();
        // Inserting customer as we now know the address for it exists
        String insertCustomerSql = "INSERT INTO customers VALUES (0, ?, ?, ?, ?, ?, ?)";
        template.update(insertCustomerSql, customer.getFirst_name(), customer.getLast_name(), customer.getPhone_number(), id, customer.getBirth_date(), customer.getDrivers_license());
    }
    public Customer findById(int id){
        String sql = "SELECT * FROM customers WHERE id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper(Customer.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Address getAddress(Customer customer){
        String sql = "SELECT * FROM addresses WHERE id = ?";
        RowMapper<Address> rowMapper = new BeanPropertyRowMapper(Address.class);
        return template.queryForObject(sql, rowMapper, customer.getAddress_id());
    }

    public ZipCode getZipCode(Address address){
        String sql = "SELECT * FROM zip_codes WHERE zip = ?";
        RowMapper<ZipCode> rowMapper = new BeanPropertyRowMapper(ZipCode.class);
        return template.queryForObject(sql, rowMapper, address.getZip_code());
    }

    public void update(int id, Customer customer){
        String sql = "UPDATE customers SET phone_number = ? WHERE id = ?";
        template.update(sql, customer.getPhone_number(), id);
    }
    public boolean delete(int id){
        String sql = "DELETE FROM customers WHERE id = ?";
        template.update(sql, id);
        return false;
    }
}
