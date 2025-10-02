package com.perfulandia.EmmanuelKena.Perfulandia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays; //ES EL ARRAY.ASLIST 
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProductoRepository;

@SpringBootTest
public class ProductoServiceTest{

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoServiceImpl;

    private Producto producto;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId(1L);
        producto.setCategoria("Perfumería");
    }


    //Método findByAll
    @Test
    void findByAllProductoTest(){
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);
        List<Producto> resultado = productoServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(productoRepository).findAll();
    }


     //Método findbyId
    @Test
    void findByIdProductoTest(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Optional<Producto> resultado = productoServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Perfumería", resultado.get().getCategoria());
        verify(productoRepository).findById(1L);

    }
    

    //Método save
    @Test
    void guardarProductoTest(){
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto saved = productoServiceImpl.crearProducto(producto);
        assertNotNull(saved);
        assertEquals("Perfumería", saved.getCategoria());
        verify(productoRepository).save(producto);
    }

     //Método modificar

    @Test
    void modificarProductoTest(){
        Producto productoModificado = new Producto();
        productoModificado.setId(1L);
        productoModificado.setCategoria("Perfumería Fancy");

        when(productoRepository.save(any(Producto.class))).thenReturn(productoModificado);
        Producto resultado = productoServiceImpl.crearProducto(productoModificado);
        assertNotNull(resultado);
        assertEquals("Perfumería Fancy", resultado.getCategoria());
        verify(productoRepository).save(productoModificado);
    }

    //Método Eliminar
    @Test
    void eliminarProductoTest(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Optional<Producto> productoEliminado = productoServiceImpl.delete(1L);
        assertTrue(productoEliminado.isPresent());
        assertEquals("Perfumería", productoEliminado.get().getCategoria());
        verify(productoRepository).findById(1L);
        verify(productoRepository).delete(producto);
        }




}
