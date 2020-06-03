package com.example.demo.services;

import com.example.demo.models.Type;
import com.example.demo.repositories.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeRepo typeRepo;

    public List<Type> fetchAll(){
        return typeRepo.fetchAll();
    }
    public void add(Type type){
        typeRepo.add(type);
    }
    public Type findById(String type){
        return typeRepo.findById(type);
    }
    public void update(String type, Type typeObject){
        typeRepo.update(type, typeObject);
    }
    public void delete(String type){
        typeRepo.delete(type);
    }
    public boolean usedInMotorHome(String type) {
        return typeRepo.usedInMotorHome(type);
    }
}
