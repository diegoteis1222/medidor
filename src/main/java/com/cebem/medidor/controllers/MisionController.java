package com.cebem.medidor.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cebem.medidor.models.Mision;
import com.cebem.medidor.models.Robot;
import com.cebem.medidor.repositories.MisionRepository;
import com.cebem.medidor.repositories.RobotRepository;
import com.cebem.medidor.services.MisionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MisionController {

    private final MisionRepository misionRepository;
    private final MisionService misionService;
    private final RobotRepository robotRepository; // <-- agrega esta línea

    @GetMapping("/misiones")
    public String listarMisiones(Model model) {
        List<Mision> misiones = misionRepository.findAll();

        // Para cada misión, obtener la lista de robots como objetos
        Map<String, List<Robot>> robotsPorMision = new HashMap<>();

        for (Mision m : misiones) {
            List<Robot> robots = new ArrayList<>();
            if (m.getRobotsParticipantes() != null) {
                for (String robotId : m.getRobotsParticipantes()) {
                    robotRepository.findById(robotId).ifPresent(robots::add);
                }
            }
            robotsPorMision.put(m.getId(), robots);
        }

        model.addAttribute("misiones", misiones);
        model.addAttribute("robotsPorMision", robotsPorMision);
        return "misiones";
    }

    @PostMapping("/misiones")
    @ResponseBody
    public Mision crearMision(@RequestBody Mision mision) {
        return misionRepository.save(mision);
    }

    @PostMapping("/misiones/{id}/asignar-robot")
    @ResponseBody
    public ResponseEntity<?> asignarRobot(@PathVariable String id, @RequestParam String robotId) {
        System.out.println("Asignando robot " + robotId + " a misión " + id);
        misionService.asignarRobot(id, robotId);
        return ResponseEntity.ok("Robot asignado correctamente");
    }
}
