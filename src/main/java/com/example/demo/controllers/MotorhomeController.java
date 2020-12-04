package com.example.demo.controllers;

import com.example.demo.models.Motorhome;
import com.example.demo.models.Type;
import com.example.demo.services.MotorhomeService;
import com.example.demo.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Coded by Ammad
 */

@Controller
//@RequestMapping(value = {"/motorhomes"})
public class MotorhomeController {
    @Autowired
    MotorhomeService motorhomeService;
    @Autowired
    TypeService typeService;

    @GetMapping("/list")
    public String motorhomeList(Model model){
        List<Motorhome> motorhomes = motorhomeService.fetchAll();
        model.addAttribute("motorhomes", motorhomes);
        return "/motorhomes/list";
    }
    @GetMapping("/create")
    public String createMotorhome(Model model){
        List<Type> types = typeService.fetchAll();
        model.addAttribute("types", types);
        model.addAttribute("motorhome", new Motorhome());
        return "/motorhomes/create";
    }

    @PostMapping("/create")
    public String addMotorhome(@ModelAttribute @Valid Motorhome motorhome, Errors errors, Model model){
        // If there are errors in the validation
        if(errors.hasErrors()) {
            List<Type> types = typeService.fetchAll();
            model.addAttribute("types", types);
            return "/motorhomes/create";
        }
        motorhomeService.add(motorhome);
        return "redirect:/motorhomes/list";
    }
    @GetMapping("/view-one/{id}")
    public String viewMotorhome(@PathVariable("id") int id, Model model) {
        model.addAttribute("motorhome", motorhomeService.findById(id));
        return "/motorhomes/view-one";
    }

    @GetMapping("/edit/{id}")
    public String editMotorhome(@PathVariable("id") int id, Model model) {
        List<Type> types = typeService.fetchAll();
        model.addAttribute("types", types);
        model.addAttribute("motorhome", motorhomeService.findById(id));
        return "/motorhomes/edit";
    }
    @PostMapping("/update")
    public String updateMotorhome(@ModelAttribute Motorhome motorhome) {
        motorhomeService.update(motorhome.getId(), motorhome);
        return "redirect:/motorhomes/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteMotorhome(@PathVariable("id") int id) {
        motorhomeService.delete(id);
        return "redirect:/motorhomes/list";
    }
}
