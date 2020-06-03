package com.example.demo.services;

import com.example.demo.models.Motorhome;
import com.example.demo.repositories.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {
    @Autowired
    MotorhomeRepo motorhomeRepo;

    public List<Motorhome> fetchAll(){
        return motorhomeRepo.fetchAll();
    }
    public void add(Motorhome motorhome){
        motorhomeRepo.add(motorhome);
    }
    public Motorhome findById(int id){
        return motorhomeRepo.findById(id);
    }
    public void update(int id, Motorhome motorhome){
        motorhomeRepo.update(id, motorhome);
    }
    public void delete(int id){
        motorhomeRepo.delete(id);
    }
}
