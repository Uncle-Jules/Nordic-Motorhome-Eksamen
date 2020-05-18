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
       // String sql = "INSERT INTO customers VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        String checkZipSql = "SELECT * FROM zip_codes WHERE zip = ?"; //Checking if the zip code is already declared.
        RowMapper<ZipCode> rowMapper = new BeanPropertyRowMapper<>(ZipCode.class);
        List<ZipCode> listZip = template.query(checkZipSql, rowMapper, customer.getZip_code_id());
        System.out.println(customer.getZip_code_id());
        if(listZip.size() == 0) { // If zip code doesn't exist, add new zip code.
            System.out.println("Zip Code if-statement");
            String insertZip = "INSERT INTO zip_codes VALUES (?, ?, ?)";
            template.update(insertZip, customer.getZip_code_id(), zipCode.getCity(), zipCode.getCountry());
        }

        String checkAddressSql = "SELECT id FROM addresses WHERE street_name = ? AND street_number = ? AND apartment_number = ?";
        RowMapper<Address> addressRowMapper = new BeanPropertyRowMapper<>(Address.class);
        List<Address> listAddress = template.query(checkAddressSql, addressRowMapper, address.getStreet_name(), address.getStreet_number(), address.getApartment_number());
        System.out.println(address.getStreet_name());
        if(listAddress.size() == 0) {
            System.out.println("Address if-statement");
            String insertAddress = "INSERT INTO addresses VALUES (0, ?, ?, ?, ?)";
            template.update(insertAddress, address.getStreet_name(), address.getStreet_number(), address.getApartment_number(), customer.getZip_code_id());
        }


        int id = template.query(checkAddressSql, addressRowMapper, address.getStreet_name(), address.getStreet_number(), address.getApartment_number()).get(0).getAddress_id();
        System.out.println(id);
        String insertCustomerSql = "INSERT INTO customers VALUES (0, ?, ?, ?, ?, ?, ?, ?)";
        template.update(insertCustomerSql, customer.getFirst_name(), customer.getLast_name(), customer.getPhone_number(), id, customer.getBirth_date(), customer.getPayment_details(), customer.getDrivers_license());
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
