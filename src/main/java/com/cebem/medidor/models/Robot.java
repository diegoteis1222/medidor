package com.cebem.medidor.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
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
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String modelo;
    private String tipo; // asalto, defensa, etc.
    private int energiaActual;
    private int energiaMaxima;
    private int nivel;

    @ElementCollection
    private List<String> habilidades;

    private String estado; // activo, dañado, destruido

    @ManyToMany(mappedBy = "robotsParticipantes")
    private List<Mision> misionesRealizadas;
}
