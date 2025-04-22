package com.cebem.medidor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cebem.medidor.models.Measure;
import com.cebem.medidor.repositories.MeasureRepository;
import com.cebem.medidor.utils.Utils;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PruebaController {

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

    // Inyeccion de dependencia
    private final MeasureRepository sensorDataRepository;

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
}
