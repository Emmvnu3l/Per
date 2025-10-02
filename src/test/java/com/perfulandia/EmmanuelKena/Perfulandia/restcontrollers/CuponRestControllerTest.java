package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.services.CuponServiceImpl;
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
public class CuponRestControllerTest {
    
    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CuponServiceImpl cuponservicioimpl;

    private List<Cupon> cuponLista;

        @Test
    public void verCuponTest() throws Exception{
        when(cuponservicioimpl.findByAll()).thenReturn(cuponLista);
        mockmvc.perform(get("/api/cupon")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

         @Test
    public void verunCuponTest(){
        //id null --> Long 
        Cupon unCupon = new Cupon(1L,"VERANO10",10.00,LocalDate.of(2025, 6, 1),LocalDate.of(2025, 6, 30),true,100);
       try{
        //Resolver dudas con finByID null cuando es 1 porque es long
        when(cuponservicioimpl.findById(1L)).thenReturn(Optional.of(unCupon));
        mockmvc.perform(get("/api/cupon/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
       }
       catch(Exception ex){
        fail("El testing lanzó un error " + ex.getMessage());
       }  
    }

    @Test
    public void cuponNoExisteTest() throws Exception{
        when(cuponservicioimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/cupon/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearCuponTest() throws Exception{
        Cupon unCupon = new Cupon(1L,"VERANO10",10.00,LocalDate.of(2025, 6, 1),LocalDate.of(2025, 6, 30),true,100);
        //ojo al usar el save, y usar el que se crea en usuarioServicioImp del main.
        when(cuponservicioimpl.crearCupon(any(Cupon.class))).thenReturn(unCupon);
        mockmvc.perform(post("/api/cupon")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unCupon)))
        .andExpect(status().isCreated());
    }
   
    @Test
    public void eliminarCuponTest() throws Exception{
    Cupon unCupon = new Cupon(1L,"VERANO10",10.00,LocalDate.of(2025, 6, 1),LocalDate.of(2025, 6, 30),true,100);
    unCupon.setId(1L);

    when(cuponservicioimpl.delete(1L)).thenReturn(Optional.of(unCupon));

    mockmvc.perform(delete("/api/cupon/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void modificarCuponTest() throws Exception{
        long cuponId = 1L;
        Cupon cuponExistente = new Cupon(1L,"VERANO10",10.00,LocalDate.of(2025, 6, 1),LocalDate.of(2025, 6, 30),true,100);
        //Cambio de Email y de Sucursal
        Cupon cuponModificado = new Cupon(1L,"VERANO10DESC",10.00,LocalDate.of(2025, 6, 1),LocalDate.of(2025, 6, 30),false,100);

        when(cuponservicioimpl.findById(cuponId)).thenReturn(Optional.of(cuponExistente));
        when(cuponservicioimpl.crearCupon(any(Cupon.class))).thenReturn(cuponModificado);
        mockmvc.perform(put("/api/cupon/{id}",cuponId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cuponModificado)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(cuponId))
        .andExpect(jsonPath("$.codigo").value("VERANO10DESC"))
        .andExpect(jsonPath("$.descuento").value(10))
        .andExpect(jsonPath("$.fechaInicio").value("2025-06-01"))
        .andExpect(jsonPath("$.fechaExpiracion").value("2025-06-30"))
        .andExpect(jsonPath("$.activo").value(false))
        .andExpect(jsonPath("$.usosDisponibles").value(100));
    }
}
