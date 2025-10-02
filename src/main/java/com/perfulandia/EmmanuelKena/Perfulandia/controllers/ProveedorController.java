package com.perfulandia.EmmanuelKena.Perfulandia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProveedorRepository;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/proveedores")
    public String verProveedor(Model model) {
        List<Proveedor> provedorsitos = (List<Proveedor>) proveedorRepository.findAll();
        model.addAttribute("provedorsitos", provedorsitos); // mismo nombre que en HTML
        return "proveedores";
    }
}
