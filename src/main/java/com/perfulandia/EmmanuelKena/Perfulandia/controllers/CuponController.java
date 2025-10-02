package com.perfulandia.EmmanuelKena.Perfulandia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.CuponRepository;

@Controller
public class CuponController {
    

    @Autowired
    private CuponRepository cuponRepository;
    
    @GetMapping("/cupones")
    public String verCupones(Model model){
        List<Cupon> cuponcitos= (List<Cupon>) cuponRepository.findAll();
        model.addAttribute("Cuponcitos", cuponcitos);
        return "cupones";

    }

}
