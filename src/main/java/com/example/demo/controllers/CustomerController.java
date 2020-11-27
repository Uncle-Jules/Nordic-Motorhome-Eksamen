package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.Customer;
import com.example.demo.models.ZipCode;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/*
 * Coded by Rasmus
 */

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
    public String createCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("address", new Address());
        model.addAttribute("zipCode", new ZipCode());
        return "/customers/create";
    }

    @PostMapping("/create")
    public String addCustomer(@ModelAttribute @Valid Customer customer, Errors errors1, @ModelAttribute @Valid Address address,
                              Errors errors2, @ModelAttribute @Valid ZipCode zipCode, Errors errors3) {
        // Each model-attribute has its own error object, so we must check all here
        if(errors1.hasErrors() || errors2.hasErrors() || errors3.hasErrors()){
            return "/customers/create";
        }
        customerService.add(customer, address, zipCode);
        return "redirect:/customers/list";
    }

    @GetMapping("/view-one/{id}")
    public String viewCustomer(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        Address address = customerService.getAddress(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("address", address);
        model.addAttribute("zipCode", customerService.getZipCode(address));
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
