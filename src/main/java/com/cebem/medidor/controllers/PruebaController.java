package com.cebem.medidor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

    @GetMapping("/saluda")
    public String saluda() {
        return "Hola desde el backend";
    }
}
