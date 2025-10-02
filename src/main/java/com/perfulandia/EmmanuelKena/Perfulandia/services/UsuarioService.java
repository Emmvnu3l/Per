package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;

public interface  UsuarioService {

    
    List<Usuario> findByAll();

    Optional<Usuario> findById(Long id);

    Usuario crearUsuario(Usuario unUsuario);

    Optional<Usuario> delete(Long id);



}
