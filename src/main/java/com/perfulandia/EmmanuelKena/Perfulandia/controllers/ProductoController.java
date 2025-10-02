package com.perfulandia.EmmanuelKena.Perfulandia.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProductoRepository;
  @Controller
public class ProductoController {
    

    @Autowired
    private ProductoRepository ProductoRepository;


    @GetMapping("/productos")
    public String verProductos(Model model){
        List<Producto> productitos= (List<Producto>) ProductoRepository.findAll();
        model.addAttribute("productitos", productitos);
        return "productos";

    }


}

