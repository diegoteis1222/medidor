package com.cebem.medidor.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void asignarRobot(Long misionId, Long robotId) {
        Mision mision = misionRepository.findById(misionId)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));

        Robot robot = robotRepository.findById(robotId)
                .orElseThrow(() -> new RuntimeException("Robot no encontrado"));

        if (mision.getRobotsParticipantes() == null) {
            mision.setRobotsParticipantes(new ArrayList<>());
        }

        if (!mision.getRobotsParticipantes().contains(robot)) {
            mision.getRobotsParticipantes().add(robot);
            misionRepository.save(mision);
        }
    }
}
