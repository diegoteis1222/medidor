package com.cebem.medidor.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.medidor.models.Movie;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final String API_KEY = "b7a596fc";
    private final String BASE_URL = "http://www.omdbapi.com/?apikey=" + API_KEY + "&t=";

    private final RestTemplate restTemplate = new RestTemplate();

    public Movie getMovieByTitle(String title) {
        String url = BASE_URL + title.replace(" ", "+");
        System.out.println("URL consultada: " + url);
        Movie movie = restTemplate.getForObject(url, Movie.class);
        System.out.println("Respuesta: " + movie);
        return movie;
    }

}
