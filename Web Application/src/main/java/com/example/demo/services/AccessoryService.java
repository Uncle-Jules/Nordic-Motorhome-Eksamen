package com.example.demo.services;


import com.example.demo.models.Accessory;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.AccessoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryService {
    @Autowired
    AccessoryRepo accessoryRepo;

    public List<Accessory> fetchAll(){
        return accessoryRepo.fetchAll();
    }
    public void add(Accessory customer){
        accessoryRepo.add(customer);
    }
    public Accessory findById(int id){
        return accessoryRepo.findById(id);
    }
    public void update(int id, Accessory accessory){
        accessoryRepo.update(id, accessory);
    }
    public void delete(int id){
        accessoryRepo.delete(id);
    }

    public List<Accessory> getReservedAccessories(Reservation reservation) {
        return accessoryRepo.getReservedAccessories(reservation);
    }
}
