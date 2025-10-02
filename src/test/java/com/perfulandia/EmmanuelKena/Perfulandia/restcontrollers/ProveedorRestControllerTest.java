package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ProveedorServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class ProveedorRestControllerTest {

     @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProveedorServiceImpl proveedorservicioImpl;

    private List<Proveedor> proveedorLista;


    //Método Service.findByall()
    @Test
    public void verProveedorTest() throws Exception{
        when(proveedorservicioImpl.findByAll()).thenReturn(proveedorLista);
        mockmvc.perform(get("/api/proveedor")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    //Método service.findByid()
    @Test
    public void verunProveedorTest(){

        Proveedor unProveedor = new Proveedor(1L, "Distribuidora Fragancias SA", "76.123.456-9", "contacto@fragancias.cl", "+56912345678", "Av. Alameda 123", "Chile", "Santiago");
       try{
        //Resolver dudas con finByID null cuando es 1 porque es long
        when(proveedorservicioImpl.findById(1L)).thenReturn(Optional.of(unProveedor));
        mockmvc.perform(get("/api/proveedor/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
       }
       catch(Exception ex){
        fail("El testing lanzó un error " + ex.getMessage());
       }  
    }


    //Método proveedorNoExisteTest
    @Test
    public void proveedorNoExisteTest() throws Exception{
        when(proveedorservicioImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/proveedor/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    //Método crearProveedorTest
    @Test
    public void proveedorTest() throws Exception{
        Proveedor unProveedor = new Proveedor(6L,"Distribuidora PerfuLady SA","76.153.416-8","contacto.perfulady.cl","+5698722444","Av Estacion Central 762","Chile","Santiago");
        //ojo al usar el save, y usar el que se crea en usuarioServicioImp del main.
        when(proveedorservicioImpl.crearProveedor(any(Proveedor.class))).thenReturn(unProveedor);
        mockmvc.perform(post("/api/proveedor")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unProveedor)))
        .andExpect(status().isCreated());
    }

    //Método eliminarproveedor
    @Test
    public void eliminarProveedorTest() throws Exception{
    Proveedor proveedor = new Proveedor(1L, "Distribuidora Fragancias SA", "76.123.456-9", "contacto@fragancias.cl", "+56912345678", "Av. Alameda 123", "Chile", "Santiago");
    proveedor.setId(1L);
    when(proveedorservicioImpl.delete(1L)).thenReturn(Optional.of(proveedor));
    mockmvc.perform(delete("/api/proveedor/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

     //Método modificarUsuario
    @Test
    public void modificarProveedorTest() throws Exception{
        long proveedorId = 1L;
        Proveedor proveedorExistente = new Proveedor(1L, "Distribuidora Fragancias SA", "76.123.456-9", "contacto@fragancias.cl", "+56912345678", "Av. Alameda 123", "Chile", "Santiago");
        //Cambio de Email y Direccion
        Proveedor proveedorModificado = new Proveedor(1L, "Distribuidora Fragancias SA", "76.123.456-9", "contacto.distribuidora@fragancias.cl", "+56912345678", "Av. Maipú 643", "Chile", "Santiago");
        when(proveedorservicioImpl.findById(proveedorId)).thenReturn(Optional.of(proveedorExistente));
        when(proveedorservicioImpl.crearProveedor(any(Proveedor.class))).thenReturn(proveedorModificado);
        mockmvc.perform(put("/api/proveedor/{id}",proveedorId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(proveedorModificado)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(proveedorId))
        .andExpect(jsonPath("$.nombre").value("Distribuidora Fragancias SA"))
        .andExpect(jsonPath("$.rut").value("76.123.456-9"))
        .andExpect(jsonPath("$.correo").value("contacto.distribuidora@fragancias.cl"))
        .andExpect(jsonPath("$.telefono").value("+56912345678"))
        .andExpect(jsonPath("$.direccion").value("Av. Maipú 643"))
        .andExpect(jsonPath("$.pais").value("Chile"))
        .andExpect(jsonPath("$.ciudad").value("Santiago"));
    }


}
