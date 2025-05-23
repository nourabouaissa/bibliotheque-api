package com.bibliotheque.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur l'API de gestion de bibliothèque !";
    }
}
