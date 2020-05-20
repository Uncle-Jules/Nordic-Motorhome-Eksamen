package com.example.demo.controllers;

import com.example.demo.models.Motorhome;
import com.example.demo.models.Type;
import com.example.demo.services.CustomerService;
import com.example.demo.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/types"})
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/list")
    public String TypeList(Model model){
        List<Type> types = typeService.fetchAll();
        model.addAttribute("types", types);
        return "home/types/list";
    }

    @GetMapping("/create")
    public String createType(Model model){
        return "home/types/create";
    }
    @PostMapping("/create")
    public String addType(@ModelAttribute Type type){
        typeService.add(type);
        return "redirect:/types/list";
    }

    @GetMapping("/view-one/{id}")
    public String viewType(@PathVariable("id") String type, Model model) {
        model.addAttribute("type", typeService.findById(type));
        return "home/types/view-one";
    }

    @GetMapping("/edit/{id}")
    public String editType(@PathVariable("id") String type, Model model) {
        System.out.println("Get mapping edit type: " + type);
        model.addAttribute("type", typeService.findById(type));
        return "home/types/edit";
    }
    @PostMapping("/update")
    public String updateType(@ModelAttribute Type type) {
        System.out.println("Post mapping edit type: " + type.getType());
        typeService.update(type.getType(), type);
        return "redirect:/types/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") String type) {
        typeService.delete(type);
        return "redirect:/types/list";
    }
}
