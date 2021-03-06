package com.example.demo.controllers;

import com.example.demo.models.Accessory;
import com.example.demo.services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* Coded by Julius
*/
@Controller
@RequestMapping(value = {"/accessories"})
public class AccessoryController {
    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/list")
    public String accessoryList(Model model) {
        List<Accessory> accessories = accessoryService.fetchAll();
        model.addAttribute("accessories", accessories);
        return "/accessories/list";
    }

    @GetMapping("/create")
    public String createAccessory(Model model) {
        model.addAttribute("accessory", new Accessory());
        return "/accessories/create";
    }

    @PostMapping("/create")
    public String addAccessories(@ModelAttribute Accessory accessory) {
        accessoryService.add(accessory);
        return "redirect:/accessories/list";
    }

    @GetMapping("/view-one/{id}")
    public String viewCustomer(@PathVariable("id") int id, Model model) {
        Accessory accessory = accessoryService.findById(id);
        model.addAttribute("accessory", accessory);
        return "/accessories/view-one";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccessory(@PathVariable("id") int id) {
        accessoryService.delete(id);
        return "redirect:/accessories/list";
    }
}

