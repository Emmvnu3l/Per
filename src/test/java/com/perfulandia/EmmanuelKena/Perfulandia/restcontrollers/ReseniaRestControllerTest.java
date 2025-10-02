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
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ReseniaServiceImpl;

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
public class ReseniaRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReseniaServiceImpl reseniaServiceImpl;

    private List<Resenia> reseniaLista;

    //Método Service.findByall()
    @Test
    public void verReseniasTest() throws Exception{
        when(reseniaServiceImpl.findByAll()).thenReturn(reseniaLista);
        mockmvc.perform(get("/api/resenia")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

     @Test
    public void verunaReseniaTest(){
        Resenia unaResenia = new Resenia(1L,101L,5L,10,"Excelente aroma, dura todo el día.",LocalDate.of(2025, 6, 1));

        try{
        when(reseniaServiceImpl.findById(1L)).thenReturn(Optional.of(unaResenia));
        mockmvc.perform(get("/api/resenia/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
       }
       catch(Exception ex){
        fail("El testing lanzó un error " + ex.getMessage());
       }  
    }

    @Test
    public void reseniaNoExisteTest() throws Exception{
        when(reseniaServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/resenia/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    //Método crearReseniaTest
    @Test
    public void crearReseniaTest() throws Exception{
        Resenia unaResenia = new Resenia(1L,101L,5L,10,"Excelente aroma, dura todo el día.",LocalDate.of(2025, 6, 1));
        //ojo al usar el save, y usar el que se crea en reseniaServicioImp del main.
        when(reseniaServiceImpl.crearResenia(any(Resenia.class))).thenReturn(unaResenia);
        mockmvc.perform(post("/api/resenia")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unaResenia)))
        .andExpect(status().isCreated());
    }
   

    //Método eliminarResenia
    @Test
    public void eliminarReseniaTest() throws Exception{
    Resenia unaResenia = new Resenia(1L,101L,5L,10,"Excelente aroma, dura todo el día.",LocalDate.of(2025, 6, 1));
    unaResenia.setId(1L);

    when(reseniaServiceImpl.delete(1L)).thenReturn(Optional.of(unaResenia));

    mockmvc.perform(delete("/api/resenia/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    //Método modificarResenia
    @Test
    public void modificarReseniaTest() throws Exception{
        long reseniaId = 1L;
        Resenia unaReseniaExistente = new Resenia(1L,101L,5L,10,"Excelente aroma, dura todo el día.",LocalDate.of(2025, 6, 1));
        //Cambio de Email y de Sucursal
        Resenia unaReseniaModificada = new Resenia(1L,101L,5L,9,"Excelente aroma, dura todo el día.",LocalDate.of(2025, 6, 1));

        when(reseniaServiceImpl.findById(reseniaId)).thenReturn(Optional.of(unaReseniaExistente));
        when(reseniaServiceImpl.crearResenia(any(Resenia.class))).thenReturn(unaReseniaModificada);
        mockmvc.perform(put("/api/resenia/{id}",reseniaId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unaReseniaModificada)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(reseniaId))
        .andExpect(jsonPath("$.productoId").value(101L))
        .andExpect(jsonPath("$.clienteId").value(5L))
        .andExpect(jsonPath("$.calificacion").value(9))
        .andExpect(jsonPath("$.comentario").value("Excelente aroma, dura todo el día."))
        .andExpect(jsonPath("$.fecha").value("2025-06-01"));
    }

}
