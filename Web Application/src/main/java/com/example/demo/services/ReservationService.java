package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Coded by All
 */

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    public List<Reservation> fetchAll(){
        return reservationRepo.fetchAll();
    }
    public Reservation findById(int id){
        return reservationRepo.findById(id);
    }
    public void add(Reservation reservation) {
        reservationRepo.add(reservation);
    }
    public void update(int id, Reservation reservation){
        reservationRepo.update(id, reservation);
    }
    public void delete(int id){
        reservationRepo.delete(id);
    }
    public void addAccessory(int reservationId, int accessoryId){
        reservationRepo.addAccessory(reservationId, accessoryId);
    }
    public boolean checkIfReserved(Reservation reservation){
        return reservationRepo.checkIfReserved(reservation);
    }
}

