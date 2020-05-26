package com.example.demo.controllers;

import com.example.demo.models.Accessory;
import com.example.demo.services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String createAccessory() {
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

    @GetMapping("/edit/{id}")
    public String editAccessory(@PathVariable("id") int id, Model model) {
        model.addAttribute("accessory", accessoryService.findById(id));
        return "/accessories/edit";
    }
    @PostMapping("/update")
    public String updateAccessory(@ModelAttribute Accessory accessory) {
        accessoryService.update(accessory.getId(), accessory);
        return "redirect:/accessories/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteAccessory(@PathVariable("id") int id) {
        accessoryService.delete(id);
        return "redirect:/accessories/list";
    }
}

