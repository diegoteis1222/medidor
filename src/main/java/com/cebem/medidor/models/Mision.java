package com.cebem.medidor.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String dificultad; // baja, media, alta
    private int recompensa;

    @ManyToMany
    private List<Robot> robotsParticipantes;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String resultado; // pendiente, éxito, fracaso
}
