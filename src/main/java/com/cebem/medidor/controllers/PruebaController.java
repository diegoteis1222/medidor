package com.cebem.medidor.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.medidor.models.Films;
import com.cebem.medidor.models.Measure;
import com.cebem.medidor.models.RickandmortyCharacter;
import com.cebem.medidor.models.Superhero;
import com.cebem.medidor.repositories.FilmsRepository;
import com.cebem.medidor.repositories.MeasureRepository;
import com.cebem.medidor.repositories.PokeApiResponse;
import com.cebem.medidor.services.PokemonService;
import com.cebem.medidor.services.RickandmortyService;
import com.cebem.medidor.services.SuperheroService;
import com.cebem.medidor.utils.Utils;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PruebaController {

    // Inyeccion de dependencias
    private final MeasureRepository sensorDataRepository;
    private final FilmsRepository filmsRepository;
    private final RickandmortyService rickandmortyService;
    private final SuperheroService superheroService;

    // http://localhost:8080/saluda
    @GetMapping("/saluda")
    public String saluda() {
        return "Hola desde el backend";
    }

    // http://localhost:8080/
    @GetMapping("/")
    public String Fin() {
        return "Aqui no hay na";
    }

    /* Este usa el metodo en Utils */
    // http://localhost:8080/palindromo/(parametro)
    @GetMapping("/palindromo/{palabra}")
    public boolean palindromo(@PathVariable String palabra) {
        return Utils.isPalindrome(palabra);
    }

    /* Este no usa metodos */
    /*
     * http://localhost:8080/palindromo/(parametro)&(parametro) ==
     * http://localhost:8080/pali?palabra=ana&valor=44
     */
    @GetMapping("/palin")
    public String palin(@RequestParam String palabra, @RequestParam String valor) {

        String inverso = new StringBuilder(palabra).reverse().toString();

        return palabra.equalsIgnoreCase(inverso) ? "Es un palindromo" : " No es un palindromo";

    }

    // http://localhost:8080/saveOnDisk
    @PostMapping("/saveOnDisk")
    public String login(@RequestParam String user, @RequestParam String passwd) {
        System.out.println("body" + user + passwd);
        return "hola";
    }

    @PostMapping("/saveMeasure")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSensorData(@RequestBody Measure sensorData) {
        sensorDataRepository.save(sensorData);
    }

    // Obtener todas las mediciones que se han guardado en la BD
    @GetMapping("/getAllMeasures")
    public Iterable<Measure> getAllMeasures() {
        return sensorDataRepository.findAll();
    }

    // otra forma de hacerlo pero mas fea
    @GetMapping("/getMeasures")
    public ResponseEntity<List<Measure>> getAllSensorData() {
        List<Measure> sensorData = sensorDataRepository.findAll();
        return ResponseEntity.ok(sensorData);
    }

    @PostMapping("/films")
    public Films createFilm(@RequestBody Films film) {
        return filmsRepository.save(film);
    }

    @GetMapping("/getAllfilms")
    public ResponseEntity<List<Films>> getAllFilms() {
        List<Films> films = filmsRepository.findAll();
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
        if (filmsRepository.existsById(id)) { // Verifica si la película existe
            filmsRepository.deleteById(id); // Elimina la película por ID
            return ResponseEntity.ok("Película eliminada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada.");
        }
    }

    @GetMapping("/top-rated-films")
    public ResponseEntity<List<Films>> getTopRatedFilms() {
        List<Films> topRatedFilms = filmsRepository.findTop10ByOrderByRatingDesc(); // Obtiene las 10 mejores películas
        return ResponseEntity.ok(topRatedFilms); // Devuelve las películas con un código HTTP 200 (OK)
    }

    @GetMapping("rickandmorty/random")
    public RickandmortyCharacter getRandomRickandmorty() {
        return rickandmortyService.getCharacterRandom();
    }

    @RestController
    @RequestMapping("/api")
    public class PokemonController {

        private final PokemonService pokemonService;

        public PokemonController(PokemonService pokemonService) {
            this.pokemonService = pokemonService;
        }

        @GetMapping("/pokemon-aleatorio")
        public PokeApiResponse getPokemonAleatorio() {
            return pokemonService.getPokemonRandom();
        }
    }

    // Endpoint para obtener un superhéroe aleatorio
    @GetMapping("/superhero/random")
    public Superhero getSuperhero() {
        return superheroService.getSuperhero();  // Obtiene un superhéroe aleatorio
    }

}
