package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;
import com.perfulandia.EmmanuelKena.Perfulandia.services.UsuarioServicioimpl;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Optional;
import org.springframework.http.MediaType;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioServicioimpl usuarioservicioimpl;

    private List<Usuario> usuarioLista;


    //Método Service.findByall()
    @Test
    public void verUsuariosTest() throws Exception{
        when(usuarioservicioimpl.findByAll()).thenReturn(usuarioLista);
        mockmvc.perform(get("/api/usuario")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    //Método service.findByid()
     @Test
    public void verunUsuarioTest(){
        Usuario unUsuario = new Usuario(1L, "María", "Peréz", "12.345.678-5", "maria.perez@email.com", "Pasaje Los Arrayanes", 102, "Sucursal Santiago Centro", "Administrador");
       try{
        when(usuarioservicioimpl.findById(1L)).thenReturn(Optional.of(unUsuario));
        mockmvc.perform(get("/api/usuario/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
       }
       catch(Exception ex){
        fail("El testing lanzó un error " + ex.getMessage());
       }  
    }

    //Método usuarioNoExisteTest
    @Test
    public void usuarioNoExisteTest() throws Exception{
        when(usuarioservicioimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/usuario/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    //Método crearUsuarioTest
    @Test
    public void crearUsuarioTest() throws Exception{
        Usuario unUsuario = new Usuario(6L, "Carlita", "Naragua", "19.998.898-2", "carli.nara@email.com", "Calle La Constitución", 1433, "Sucursal Santiago Centro", "Empleado");
        when(usuarioservicioimpl.crearUsuario(any(Usuario.class))).thenReturn(unUsuario);
        mockmvc.perform(post("/api/usuario")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unUsuario)))
        .andExpect(status().isCreated());
    }
   

    //Método eliminarUsuario
    @Test
    public void eliminarUsuarioTest() throws Exception{
    Usuario usuario = new Usuario(1L, "María", "Pérez", "12.345.678-5", "maria.perez@email.com", "Pasaje Los Arrayanes", 102, "Sucursal Santiago Centro", "Administrador"); 
    usuario.setId(1L);

    when(usuarioservicioimpl.delete(1L)).thenReturn(Optional.of(usuario));

    mockmvc.perform(delete("/api/usuario/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    //Método modificarUsuario
    @Test
    public void modificarUsuarioTest() throws Exception{
        long usuarioId = 1L;
        Usuario usuarioExistente = new Usuario(usuarioId, "María", "Pérez", "12.345.678-5", "maria.perez@email.com", "Pasaje Los Arrayanes", 102, "Sucursal Santiago Centro", "Administrador");
        //Cambio de Email y de Sucursal
        Usuario usuarioModificado = new Usuario(usuarioId, "María", "Pérez", "12.345.678-5", "m2.perez@email.com", "Pasaje Los Arrayanes", 102, "Sucursal La Florida", "Administrador");

        when(usuarioservicioimpl.findById(usuarioId)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioservicioimpl.crearUsuario(any(Usuario.class))).thenReturn(usuarioModificado);
        mockmvc.perform(put("/api/usuario/{id}",usuarioId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(usuarioModificado)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(usuarioId))
        .andExpect(jsonPath("$.nombre").value("María"))
        .andExpect(jsonPath("$.apellido").value("Pérez"))
        .andExpect(jsonPath("$.rut").value("12.345.678-5"))
        .andExpect(jsonPath("$.email").value("m2.perez@email.com"))
        .andExpect(jsonPath("$.direccion").value("Pasaje Los Arrayanes"))
        .andExpect(jsonPath("$.numero").value(102))
        .andExpect(jsonPath("$.sucursal").value("Sucursal La Florida"))
        .andExpect(jsonPath("$.rol").value("Administrador"));
    }

}
