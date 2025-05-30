package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cebem.medidor.models.Movie;
import com.cebem.medidor.services.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/peli")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("title") String title, Model model) {
        Movie movie = movieService.getMovieByTitle(title);

        if (movie == null || !"True".equalsIgnoreCase(movie.getResponse())) {
            model.addAttribute("error", movie != null ? movie.getError() : "Error al obtener datos");
            return "index"; // o una página de error si prefieres
        }

        model.addAttribute("movie", movie);
        return "result";
    }

}
