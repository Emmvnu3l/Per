package com.perfulandia.EmmanuelKena.Perfulandia.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

        @Autowired
    private UsuarioRepository UsuarioRepository;


    @GetMapping("/usuarios")
    public String verUsuarios(Model model){
        List<Usuario> usuaritos= (List<Usuario>) UsuarioRepository.findAll();
        model.addAttribute("Usuaritos", usuaritos);
        return "usuarios";

    }
    
}
