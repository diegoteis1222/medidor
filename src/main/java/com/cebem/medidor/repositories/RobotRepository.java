package com.cebem.medidor.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cebem.medidor.models.Robot;

@Repository
public interface RobotRepository extends MongoRepository<Robot, String> {
    // Métodos personalizados si necesitas
}
