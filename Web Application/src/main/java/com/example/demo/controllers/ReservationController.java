package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MotorhomeService;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Controller
@RequestMapping(value = {"/reservations"})
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    CustomerService customerService;
    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/list")
    public String reservationList(Model model) {
        List<Reservation> reservations = reservationService.fetchAll();
        List<Customer> customers = customerService.fetchAll();
        List<Motorhome> motorhomes = motorhomeService.fetchAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("customers", customers);
        model.addAttribute("motorhomes", motorhomes);
        return "/reservations/list";
    }

    @GetMapping("/view-one/{id}")
    public String viewReservation(@PathVariable("id") int id, Model model) {
        Reservation reservation = reservationService.findById(id);
        List<Accessory> accessories = accessoryService.getReservedAccessories(reservation);
        Customer customer = customerService.findById(reservation.getCustomer_id());
        Motorhome motorhome = motorhomeService.findById(reservation.getMotorhome_id());
        Address address = customerService.getAddress(customer);
        ZipCode zipCode = customerService.getZipCode(address);

        model.addAttribute("reservation", reservation);
        model.addAttribute("accessories", accessories);
        model.addAttribute("customer", customer);
        model.addAttribute("motorhome", motorhome);
        model.addAttribute("address", address);
        model.addAttribute("zipCode", zipCode);
        return "/reservations/view-one";
    }

    @GetMapping("/create")
    public String createReservation(Model model) {
        List<Motorhome> motorhomes = motorhomeService.fetchAll();
        List<Customer> customers = customerService.fetchAll();
        model.addAttribute("motorhomes", motorhomes);
        model.addAttribute("customers", customers);
        return "/reservations/create";
    }

    @PostMapping("/create")
    public String addReservation(@ModelAttribute Reservation reservation) {
        reservationService.add(reservation);
        return "redirect:/reservations/list";
    }


    @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable("id") int id, Model model) {
        model.addAttribute("reservation", reservationService.findById(id));
        return "/reservations/edit";
    }
    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationService.update(reservation.getId(), reservation);
        return "redirect:/reservations/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
        reservationService.delete(id);
        return "redirect:/reservations/list";
    }
}

