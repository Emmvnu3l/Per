package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServicioimpl usuarioServicioimpl;

    private Usuario usuario;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("María");
    }


    //Método findByAll
    @Test
    void findByAllUsuarioTest(){
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> resultado = usuarioServicioimpl.findByAll();
        assertEquals(1, resultado.size());
        verify(usuarioRepository).findAll();
    }


    //Método findbyId
    @Test
    void findByIdUsuarioTest(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> resultado = usuarioServicioimpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("María", resultado.get().getNombre());
        verify(usuarioRepository).findById(1L);

    }


    //Método save
    @Test
    void guardarUsuarioTest(){
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario saved = usuarioServicioimpl.crearUsuario(usuario);
        assertNotNull(saved);
        assertEquals("María", saved.getNombre());
        verify(usuarioRepository).save(usuario);
    }



    //Método modificar

    @Test
    void modificarUsuarioTest(){
        Usuario usuarioModificado = new Usuario();
        usuarioModificado.setId(1L);
        usuarioModificado.setNombre("Maria Isabel");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioModificado);
        Usuario resultado = usuarioServicioimpl.crearUsuario(usuarioModificado);
        assertNotNull(resultado);
        assertEquals("Maria Isabel", resultado.getNombre());
        verify(usuarioRepository).save(usuarioModificado);
    }


    //Método Eliminar
    @Test
    void eliminarUsuarioTest(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Optional<Usuario> usuarioEliminado = usuarioServicioimpl.delete(1L);
        assertTrue(usuarioEliminado.isPresent());
        assertEquals("María", usuarioEliminado.get().getNombre());
        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).delete(usuario);
        }


}
