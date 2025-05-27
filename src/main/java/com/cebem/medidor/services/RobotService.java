package com.cebem.medidor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cebem.medidor.models.Robot;
import com.cebem.medidor.repositories.RobotRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final RobotRepository robotRepository;

    public Robot crear(Robot robot) {
        return robotRepository.save(robot);
    }

    public List<Robot> listar() {
        return robotRepository.findAll();
    }

    public void recargar(String id) {
        Optional<Robot> optional = robotRepository.findById(id);
        if (optional.isPresent()) {
            Robot robot = optional.get();
            robot.setEnergiaActual(robot.getEnergiaMaxima());
            robotRepository.save(robot);
        }
    }

    public void subirNivel(String id) {
        Optional<Robot> optional = robotRepository.findById(id);
        if (optional.isPresent()) {
            Robot robot = optional.get();
            robot.setNivel(robot.getNivel() + 1);
            robotRepository.save(robot);
        }
    }
}
