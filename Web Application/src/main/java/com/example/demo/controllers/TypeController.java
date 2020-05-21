package com.example.demo.controllers;

import com.example.demo.models.Motorhome;
import com.example.demo.models.Type;
import com.example.demo.services.CustomerService;
import com.example.demo.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = {"/types"})
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/list")
    public String typeList(Model model){
        List<Type> types = typeService.fetchAll();
        model.addAttribute("types", types);
        return "home/types/list";
    }

    @GetMapping("/create")
    public String createType(){
        return "home/types/create";
    }
    @PostMapping("/create")
    public String addType(@ModelAttribute Type type, RedirectAttributes redirectAttributes){
        // If type does not already exists it is created and user is returned to list of types
        if(typeService.findById(type.getType()) == null){
            typeService.add(type);
            return "redirect:/types/list";
        }
        // If type already exists user is notified and remains on the page
        String failedMessage = String.format("Unable to add type - type with name '%s' already exists", type.getType());
        redirectAttributes.addFlashAttribute("message", failedMessage);
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        return "redirect:/types/create";
    }

    @GetMapping("/view-one/{id}")
    public String viewType(@PathVariable("id") String type, Model model) {
        model.addAttribute("type", typeService.findById(type));
        return "home/types/view-one";
    }

    @GetMapping("/edit/{id}")
    public String editType(@PathVariable("id") String type, Model model) {
        model.addAttribute("type", typeService.findById(type));
        return "home/types/edit";
    }
    @PostMapping("/update")
    public String updateType(@ModelAttribute Type type) {
        typeService.update(type.getType(), type);
        return "redirect:/types/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") String type, RedirectAttributes redirectAttributes) {
        // If type is used in a motorhome the user is notified and unable to delete
        if (typeService.usedInMotorHome(type)) {
            String failedMessage = "Unable to delete - type is already used in one or more motorhomes";
            redirectAttributes.addFlashAttribute("message", failedMessage);
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/types/list";
        }
        // If type is not used in a motorhome the type is simply deleted
        typeService.delete(type);
        return "redirect:/types/list";
    }
}
