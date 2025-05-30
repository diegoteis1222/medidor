package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cebem.medidor.models.Superhero;
import com.cebem.medidor.services.SuperheroService;

@Controller
public class SuperheroController {

    private SuperheroService superheroService;

    // Constructor sin @Autowired
    public SuperheroController() {
        // Creación manual de la instancia del servicio
        this.superheroService = new SuperheroService();
    }

    @GetMapping("/superhero")
    public String getRandomSuperhero(Model model) {
        // Obtener el superhéroe aleatorio desde el servicio
        Superhero superhero = superheroService.getSuperhero();

        // Añadir el superhéroe al modelo para que Thymeleaf lo utilice en la vista
        model.addAttribute("superhero", superhero);

        // Devolver el nombre de la vista "superhero" que Thymeleaf usará
        return "superhero";  // Esto hará que se renderice el archivo superhero.html
    }
}