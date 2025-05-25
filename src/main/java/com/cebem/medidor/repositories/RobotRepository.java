package com.cebem.medidor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cebem.medidor.models.Robot;

@Repository
public interface RobotRepository extends JpaRepository<Robot, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
}
