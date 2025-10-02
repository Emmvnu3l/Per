package com.perfulandia.EmmanuelKena.Perfulandia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.VentaRepository;

@Controller
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;
    
    @GetMapping("/ventas")
    public String verVentas(Model model){
        List<Venta> ventitas= (List<Venta>) ventaRepository.findAll();
        model.addAttribute("Ventitas", ventitas);
        return "ventas";

    }
    
}
