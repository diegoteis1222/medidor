package com.cebem.medidor.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.medidor.repositories.PokeApiResponse;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokeApiResponse getPokemonRandom() {
        int id = (int) (Math.random() * 151 + 1); // Pokémon del 1 al 151 (primera generación)
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        return restTemplate.getForObject(url, PokeApiResponse.class);
    }
}
