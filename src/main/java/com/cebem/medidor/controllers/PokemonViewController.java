package com.cebem.medidor.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cebem.medidor.repositories.PokeApiResponse;
import com.cebem.medidor.services.PokemonService;

@Controller
public class PokemonViewController {

    private final PokemonService pokemonService;

    public PokemonViewController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon")
    public String mostrarPokemon(Model model) {
        PokeApiResponse pokeApiResponse = pokemonService.getPokemonRandom();

        String nombre = capitalize(pokeApiResponse.getName());

        // Obtener todos los tipos y unirlos por coma
        String tipos = pokeApiResponse.getTypes().stream()
                .map(typeSlot -> typeSlot.getType().getName())
                .collect(Collectors.joining(", "));

        String imagen = pokeApiResponse.getSprites().getFront_default();

        model.addAttribute("nombre", nombre);
        model.addAttribute("tipo", tipos); // <-- cambia de "tipo" a "tipos"
        model.addAttribute("imagen", imagen);

        return "pokemon";
    }

    // pa conectar: http://localhost:8080/pokemon

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
