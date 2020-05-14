package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
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
    public String createCustomer(Model model) {
        List<Customer> customers = customerService.fetchAll();
        model.addAttribute("customer", customers);
        return "home/customers/create";
    }

    @PostMapping("/customers/create")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.add(customer);
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
}
