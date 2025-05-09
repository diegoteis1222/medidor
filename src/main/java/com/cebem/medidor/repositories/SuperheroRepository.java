package com.cebem.medidor.repositories;

import com.cebem.medidor.models.Superhero;

public interface SuperheroRepository {
    Superhero getSuperheroById(String id);
    }
