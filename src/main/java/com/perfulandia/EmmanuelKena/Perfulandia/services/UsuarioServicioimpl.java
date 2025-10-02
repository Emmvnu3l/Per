package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.UsuarioRepository;


@Service
public class UsuarioServicioimpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;


    /*Encontrar todo */

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() { 
        return (List<Usuario>) usuarioRepository.findAll();
    }


    /*Encontrar por id */
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
       return usuarioRepository.findById(id);
    }

    /*Crear usuario */
    @Override
    @Transactional
    public Usuario crearUsuario(Usuario unUsuario) {
     return usuarioRepository.save(unUsuario);
    }

    /*Eliminar usuario */
    @Override
    @Transactional
    public Optional<Usuario> delete(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.ifPresent(usuarioRepository::delete);
        return usuarioOptional;
    }
  }




