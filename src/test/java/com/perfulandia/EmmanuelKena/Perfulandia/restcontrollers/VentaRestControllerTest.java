package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;
import com.perfulandia.EmmanuelKena.Perfulandia.services.VentaServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.MediaType;




@SpringBootTest
@AutoConfigureMockMvc
public class VentaRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VentaServiceImpl ventaServiceImpl;

    private List<Venta> ventaLista;

    //Método Service.findByall()
    @Test
    public void verVentaTest() throws Exception{
        when(ventaServiceImpl.findByAll()).thenReturn(ventaLista);
         mockmvc.perform(get("/api/venta")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    //Método service.findByid()
    @Test
    public void verunaVentaTest(){
        Venta unaVenta = new Venta(1L, 101L, LocalDate.of(2025, 06, 01), 45.000, "Tarjeta","Completada",1L);
        try{
            when(ventaServiceImpl.findById(1L)).thenReturn(Optional.of(unaVenta));
            mockmvc.perform(get("/api/venta")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
        catch(Exception ex){
        fail("El testing lanzó un error " + ex.getMessage());
    }
}

    //Método ventaNoExisteTest
        @Test
        public void ventaNoEncontradaTest() throws Exception{
            when(ventaServiceImpl.findById(10L)).thenReturn(Optional.empty());
            mockmvc.perform(get("/api/venta")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        
    //Método crearVentaTest
    @Test
    public void crearVentaTest() throws Exception{
        Venta unaVenta = new Venta(6L, 106L, LocalDate.of(2025, 06, 06), 83.000, "Efectivo","Completada",4L);
        when(ventaServiceImpl.crearVenta(any(Venta.class))).thenReturn(unaVenta);
            mockmvc.perform(post("/api/venta")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unaVenta)))
            .andExpect(status().isCreated());     
    }

    //Método eliminarVenta
    @Test
    public void eliminarVentaTest() throws Exception{
        Venta venta = new Venta(1L, 101L, LocalDate.of(2025, 06, 01), 45.000, "Tarjeta","Completada",1L);
        venta.setId(1L);

        when(ventaServiceImpl.delete(1L)).thenReturn(Optional.of(venta));
        mockmvc.perform(delete("/api/venta/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    //Método modificarVenta
    @Test
    public void modificarVentaTest() throws Exception{
        long ventaId = 1L;
        Venta ventaExistente = new Venta(1L, 101L, LocalDate.of(2025, 06, 01), 45.000, "Tarjeta","Completada",1L);
        //cambio de Total, Método de pago y estado
        Venta ventaModificada = new Venta(1L, 101L, LocalDate.of(2025, 06, 01), 55.000, "Transferencia","Pendiente",1L);

        when(ventaServiceImpl.findById(ventaId)).thenReturn(Optional.of(ventaExistente));
        when(ventaServiceImpl.crearVenta(any(Venta.class))).thenReturn(ventaModificada);
        when(ventaServiceImpl.findById(10L)).thenReturn(Optional.empty());
            mockmvc.perform(put("/api/venta/{id}",ventaId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(ventaModificada)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(ventaId))
            .andExpect(jsonPath("$.clienteId").value(101L))
            .andExpect(jsonPath("$.fecha").value("2025-06-01"))
            .andExpect(jsonPath("$.total").value(55.000))
            .andExpect(jsonPath("$.metodoPago").value("Transferencia"))
            .andExpect(jsonPath("$.estado").value("Pendiente"))
            .andExpect(jsonPath("$.cuponId").value(1L));
    }



}
