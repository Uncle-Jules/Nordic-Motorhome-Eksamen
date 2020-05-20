package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.Customer;
import com.example.demo.models.Motorhome;
import com.example.demo.models.ZipCode;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CustomerService customerService;
    @Autowired
    MotorhomeService motorhomeService;

    @GetMapping("/")
    public String index() {
        return "home/index";
    }



}
