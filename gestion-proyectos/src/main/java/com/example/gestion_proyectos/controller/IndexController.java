package com.example.gestion_proyectos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        // Redirige a la lista de proyectos
        return "redirect:/proyectos";
    }
}
