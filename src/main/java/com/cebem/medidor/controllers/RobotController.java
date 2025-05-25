package com.cebem.medidor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cebem.medidor.models.Robot;
import com.cebem.medidor.services.RobotService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/robots")
public class RobotController {
    private final RobotService robotService;

    @GetMapping
    public String listaRobots(Model model) {
        model.addAttribute("robots", robotService.listar());
        return "robots";
    }

    @PostMapping
    @ResponseBody
    public Robot crear(@RequestBody Robot robot) {
        return robotService.crear(robot);
    }

    @PostMapping("/{id}/recargar")
    public String recargar(@PathVariable Long id) {
        robotService.recargar(id);
        return "redirect:/robots";
    }

    @PostMapping("/{id}/subir-nivel")
    public String subirNivel(@PathVariable Long id) {
        robotService.subirNivel(id);
        return "redirect:/robots";
    }

}
