package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ProductoServiceImpl;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoRestControllerTest {


 @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductoServiceImpl productoServiceImpl;

    private List<Producto> productoLista;

    //Método service.finbyall
    @Test
    public void verProductoTest() throws Exception{
        when(productoServiceImpl.findByAll()).thenReturn(productoLista);
        mockmvc.perform(get("/api/producto")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }


    
    //Método service.findByid()
    @Test
    public void verunProductoTest(){
        Producto unProducto = new Producto(1L,"Perfume Élite","Aroma intenso y duradero","Dior","Perfumería","Unisex",50 ,45990 ,"Amaderada",100);
        try{
            when(productoServiceImpl.findById(1L)).thenReturn(Optional.of(unProducto));
            mockmvc.perform(get("/api/producto")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzó un error " + ex.getMessage());
        }        
    }

     //Método productoNoExisteTest
        @Test
        public void productoNoEncontradoTest() throws Exception{
            when(productoServiceImpl.findById(10L)).thenReturn(Optional.empty());
            mockmvc.perform(get("/api/producto")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }

    //Método crearProductoTest
    @Test
    public void crearProductoTest() throws Exception{
        Producto unProducto = new Producto(6L,"Perfume Black Opium","Aroma suave y romántico",
        " Yves Saint Laurent","Perfumería","Mujer",15,147990 ,"Floral",90);

        when(productoServiceImpl.crearProducto(any(Producto.class))).thenReturn(unProducto);
        mockmvc.perform(post("/api/producto")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(unProducto)))
        .andExpect(status().isCreated());
    }

    //Método eliminarProducto
    @Test   
    public void eliminarProductoTest() throws Exception{
        Producto unProducto = new Producto(1L,"Perfume Élite","Aroma intenso y duradero","Dior","Perfumería","Unisex",50 ,45990 ,"Amaderada",100);
        unProducto.setId(1L);

    when(productoServiceImpl.delete(1L)).thenReturn(Optional.of(unProducto));
        
    mockmvc.perform(delete("/api/producto/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    //Método modificarProduto
    @Test
    public void modificarProductoTest() throws Exception{
        long productoId = 1L;
        Producto productoExistente = new Producto(1L,"Perfume Élite","Aroma intenso y duradero","Dior","Perfumería","Unisex",50 ,45990 ,"Amaderada",100);
        //cambio de precio y stock
        Producto productoModificado = new Producto(1L,"Perfume Élite","Aroma intenso y duradero","Dior","Perfumería","Unisex",33 ,40990 ,"Amaderada",100);

        when(productoServiceImpl.findById(productoId)).thenReturn(Optional.of(productoExistente));
        when(productoServiceImpl.crearProducto(any(Producto.class))).thenReturn(productoModificado);
        when(productoServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(put("/api/producto/{id}", productoId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productoModificado)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(productoId))
            .andExpect(jsonPath("$.nombre").value("Perfume Élite"))
            .andExpect(jsonPath("$.descripcion").value("Aroma intenso y duradero"))
            .andExpect(jsonPath("$.marca").value("Dior"))
            .andExpect(jsonPath("$.categoria").value("Perfumería"))
            .andExpect(jsonPath("$.genero").value("Unisex"))
            .andExpect(jsonPath("$.stock").value(33))
            .andExpect(jsonPath("$.precio").value(40990))
            .andExpect(jsonPath("$.fragancia").value("Amaderada"))
            .andExpect(jsonPath("$.mililitros").value(100));
    }

}
