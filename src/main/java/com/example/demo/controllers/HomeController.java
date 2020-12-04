package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Coded by All
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "home/index";
    }
}
