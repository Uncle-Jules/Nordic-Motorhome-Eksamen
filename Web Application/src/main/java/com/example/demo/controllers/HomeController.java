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

    @GetMapping("/customers/list")
    public String customerList(Model model) {
        List<Customer> customers = customerService.fetchAll();
        model.addAttribute("customers", customers);
        return "home/customers/list";
    }

    @GetMapping("/customers/create")
    public String createCustomer() {
        return "home/customers/create";
    }

    @PostMapping("/customers/create")
    public String addCustomer(@ModelAttribute Customer customer, @ModelAttribute Address address, @ModelAttribute ZipCode zipCode) {
        customerService.add(customer, address, zipCode);
        return "redirect:/customers/list";
    }

    @GetMapping("/customers/view-one/{id}")
    public String viewCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "home/customers/view-one";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "home/customers/edit";
    }
    @PostMapping("/customers/update")
    public String updateCar(@ModelAttribute Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customers/list";
    }
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        customerService.delete(id);
        return "redirect:/customers/list";
    }


    @GetMapping("/motorhomes/list")
    public String motorhomeList(Model model){
        List<Motorhome> motorhomes = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomes);
        return "home/motorhomes/list";
    }
    @GetMapping("/motorhomes/create")
    public String createMotorhome(Model model){
        return "home/motorhomes/create";
    }
    @PostMapping("motorhomes/create")
    public String addMotorhome(@ModelAttribute Motorhome motorhome){
        motorhomeService.add(motorhome);
        return "redirect:/motorhomes/list";
    }
    @GetMapping("motorhomes/view-one/{id}")
    public String viewMotorhome(@PathVariable("id") int id, Model model) {
        model.addAttribute("motorhome", motorhomeService.findById(id));
        return "home/motorhomes/view-one";
    }
    @PostMapping("/motorhomes/update")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.update(motorhome.getId(), motorhome);
        return "redirect:/motorhomes/list";
    }
    @GetMapping("/motorhomes/delete/{id}")
    public String deleteMotorhome(@PathVariable("id") int id) {
        motorhomeService.delete(id);
        return "redirect:/motorhomes/list";
    }
}
