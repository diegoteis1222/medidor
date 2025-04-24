package com.cebem.medidor.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cebem.medidor.models.Films;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Long> {

    Optional<Films> findById(Long id);

    List<Films> findByDirector(String director);

    List<Films> findByGenre(String genre); 

    // Método para obtener las 10 películas mejor valoradas
    List<Films> findTop10ByOrderByRatingDesc();
}