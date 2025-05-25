package com.cebem.medidor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cebem.medidor.models.Mision;

@Repository
public interface MisionRepository extends JpaRepository<Mision, Long> {
    // Métodos para filtrar por dificultad o resultado si los necesitas
}