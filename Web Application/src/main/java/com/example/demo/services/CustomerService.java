package com.example.demo.services;

import com.example.demo.models.Address;
import com.example.demo.models.Customer;
import com.example.demo.models.ZipCode;
import com.example.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> fetchAll(){
        return customerRepo.fetchAll();
    }
    public void add(Customer customer, Address address, ZipCode zipCode){
        customerRepo.add(customer, address, zipCode);
    }
    public Customer findById(int id){
        return customerRepo.findById(id);
    }
    public Address getAddress(Customer customer) {
        return customerRepo.getAddress(customer);
    }
    public ZipCode getZipCode(Address address) {
        return customerRepo.getZipCode(address);
    }
    public void update(int id, Customer c){
        customerRepo.update(id, c);
    }
    public void delete(int id){
        customerRepo.delete(id);
    }
}
