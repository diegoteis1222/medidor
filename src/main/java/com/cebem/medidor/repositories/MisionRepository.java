package com.cebem.medidor.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cebem.medidor.models.Mision;

@Repository
public interface MisionRepository extends MongoRepository<Mision, String> {
    // Métodos custom si necesitas
}
