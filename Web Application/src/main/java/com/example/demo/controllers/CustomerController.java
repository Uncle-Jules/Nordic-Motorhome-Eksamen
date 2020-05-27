package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.Customer;
import com.example.demo.models.ZipCode;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/customers"})
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String customerList(Model model) {
        List<Customer> customers = customerService.fetchAll();
        model.addAttribute("customers", customers);
        return "/customers/list";
    }

    @GetMapping("/create")
    public String createCustomer() {
        return "/customers/create";
    }

    @PostMapping("/create")
    public String addCustomer(@ModelAttribute Customer customer, @ModelAttribute Address address, @ModelAttribute ZipCode zipCode) {

        customerService.add(customer, address, zipCode);
        return "redirect:/customers/list";
    }

    @GetMapping("/view-one/{id}")
    public String viewCustomer(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        Address address = customerService.getAddress(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("address", address);
        model.addAttribute("zip_code", customerService.getZipCode(address));
        return "/customers/view-one";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/customers/edit";
    }
    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customers/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        customerService.delete(id);
        return "redirect:/customers/list";
    }
}
