package com.cebem.medidor.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cebem.medidor.models.Mision;
import com.cebem.medidor.models.Robot;
import com.cebem.medidor.repositories.MisionRepository;
import com.cebem.medidor.repositories.RobotRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MisionService {

    private final MisionRepository misionRepository;
    private final RobotRepository robotRepository;

    public void asignarRobot(String misionId, String robotId) {
        Mision mision = misionRepository.findById(misionId)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));

        robotRepository.findById(robotId)
                .orElseThrow(() -> new RuntimeException("Robot no encontrado"));

        if (mision.getRobotsParticipantes() == null) {
            mision.setRobotsParticipantes(new ArrayList<>());
        }

        if (!mision.getRobotsParticipantes().contains(robotId)) {
            mision.getRobotsParticipantes().add(robotId);
            misionRepository.save(mision);
        }
    }

}
