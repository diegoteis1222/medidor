package com.cebem.medidor.services;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.medidor.models.Superhero;


@Service
public class SuperheroService {

    // Token de la API de superheroapi
    private static final String API_TOKEN = "53a330fee032b2945318a4fcae9cfa50";  // Token de autenticación

    public Superhero getSuperhero() {
        // Generamos un ID aleatorio (en este ejemplo, entre 1 y 731, ajusta según lo que soporte la API)
        Random rand = new Random();
        int randomId = rand.nextInt(731) + 1;  // Número entre 1 y 731

        // Concatenamos la URL de la API con el token y el ID aleatorio
        String url = "https://superheroapi.com/api/" + API_TOKEN + "/" + randomId;

        // Creamos un objeto RestTemplate para hacer la solicitud HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Realizamos la solicitud HTTP y obtenemos el cuerpo de la respuesta
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Procesamos la respuesta para extraer los datos
        String responseBody = response.getBody();

        // Creamos un objeto Superhero y lo llenamos con los datos de la respuesta
        Superhero superhero = new Superhero();

        // Extraemos los valores del JSON utilizando índices
        int nameStartIndex = responseBody.indexOf("\"name\":\"") + 8;
        int nameEndIndex = responseBody.indexOf("\"", nameStartIndex);
        String name = responseBody.substring(nameStartIndex, nameEndIndex);

        int imageUrlStartIndex = responseBody.indexOf("\"url\":\"") + 7;
        int imageUrlEndIndex = responseBody.indexOf("\"", imageUrlStartIndex);
        String imageUrl = responseBody.substring(imageUrlStartIndex, imageUrlEndIndex);

        int powerStartIndex = responseBody.indexOf("\"powerstats\":") + 13;
        int powerEndIndex = responseBody.indexOf("}", powerStartIndex);
        String power = responseBody.substring(powerStartIndex, powerEndIndex);

        int workStartIndex = responseBody.indexOf("\"occupation\":\"") + 15;
        int workEndIndex = responseBody.indexOf("\"", workStartIndex);
        String work = responseBody.substring(workStartIndex, workEndIndex);

        // Asignamos los valores extraídos al objeto Superhero
        superhero.setName(name);
        superhero.setImageUrl(imageUrl);
        superhero.setPower(power);
        superhero.setWork(work);

        return superhero;
    }
}