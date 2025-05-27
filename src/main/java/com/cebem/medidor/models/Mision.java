package com.cebem.medidor.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "misiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mision {
    @Id
    private String id;  // Mongo usa String para el _id

    private String nombre;
    private String descripcion;
    private String dificultad; // baja, media, alta
    private int recompensa;


    private List<String> robotsParticipantes;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String resultado; // pendiente, éxito, fracaso
}
