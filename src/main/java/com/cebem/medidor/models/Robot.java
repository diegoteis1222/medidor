package com.cebem.medidor.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "robots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Robot {

    @Id
    private String id;  // MongoDB usa String para _id

    private String nombre;
    private String modelo;
    private String tipo; // asalto, defensa, etc.
    private int energiaActual;
    private int energiaMaxima;
    private int nivel;

    private List<String> habilidades;  // MongoDB guarda listas nativamente

    private String estado; // activo, dañado, destruido

    @DBRef
    private List<Mision> misionesRealizadas;  // referencia a misiones
}
