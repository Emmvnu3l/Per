package com.perfulandia.EmmanuelKena.Perfulandia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ReseniaRepository;

@Controller
public class ReseniaController {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @GetMapping("/resenias")
    public String verResenias(Model model){
        List<Resenia> resenias= (List<Resenia>) reseniaRepository.findAll();
        model.addAttribute("Resenias", resenias);
        return "reseñas";

    }


}
