package com.cebem.medidor.controllers;

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
        String tipo = pokeApiResponse.getTypes().get(0).getType().getName();
        String imagen = pokeApiResponse.getSprites().getFront_default();

        model.addAttribute("nombre", nombre);
        model.addAttribute("tipo", tipo);
        model.addAttribute("imagen", imagen);

        return "pokemon";
    }
    // pa conectar: http://localhost:8080/pokemon

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

