package com.cebem.medidor.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cebem.medidor.models.Mision;
import com.cebem.medidor.repositories.MisionRepository;
import com.cebem.medidor.services.MisionService;

@Controller
public class MisionController {

    private final MisionRepository misionRepository;
    private final MisionService misionService;

    // Inyecta ambos en el constructor
    public MisionController(MisionRepository misionRepository, MisionService misionService) {
        this.misionRepository = misionRepository;
        this.misionService = misionService;
    }

    @GetMapping("/misiones")
    public String listarMisiones(Model model) {
        List<Mision> misiones = misionRepository.findAll();
        model.addAttribute("misiones", misiones);
        return "misiones"; // nombre de la plantilla Thymeleaf (misiones.html)
    }

    @PostMapping("/misiones")
    @ResponseBody
    public Mision crearMision(@RequestBody Mision mision) {
        return misionRepository.save(mision);
    }

    @PostMapping("/misiones/{id}/asignar-robot")
    public String asignarRobot(@PathVariable Long id, @RequestParam Long robotId) {
        misionService.asignarRobot(id, robotId);
        return "redirect:/misiones";
    }

}
