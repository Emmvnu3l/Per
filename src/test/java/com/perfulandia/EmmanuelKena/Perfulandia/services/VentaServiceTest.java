package com.perfulandia.EmmanuelKena.Perfulandia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays; //ES EL ARRAY.ASLIST 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.VentaRepository;

@SpringBootTest
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaServiceImpl;

    private Venta venta;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        venta = new Venta();
        venta.setId(1L);
        venta.setMetodoPago("Tarjeta");
    }


    //Método findByAll
    @Test
    void finByAllVentaTest(){
        List<Venta> ventas = Arrays.asList(venta);
        when(ventaRepository.findAll()).thenReturn(ventas);
        List<Venta> resultado = ventaServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(ventaRepository).findAll();

    }


    //Método findbyid
    @Test
    void finByVentaTest(){
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        Optional<Venta> resultado = ventaServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Tarjeta", resultado.get().getMetodoPago());
        verify(ventaRepository).findById(1L);
    }

    //Método save
    @Test
    void guardarVentaId(){
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        Venta saved = ventaServiceImpl.crearVenta(venta);
        assertNotNull(saved);
        assertEquals("Tarjeta", saved.getMetodoPago());
        verify(ventaRepository).save(venta);

    }

        //Método modificar
    @Test
    void modificarVentaTest(){
        Venta ventaModificada = new Venta();
        ventaModificada.setId(1L);
        ventaModificada.setMetodoPago("Transferencia");

        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaModificada);
        Venta resultado = ventaServiceImpl.crearVenta(ventaModificada);
        assertNotNull(resultado);
        assertEquals("Transferencia", resultado.getMetodoPago());
        verify(ventaRepository).save(ventaModificada);
        
    }

    //Método Eliminar
    @Test 
    void eliminarVentaTest(){
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        Optional<Venta> ventaEliminada = ventaServiceImpl.delete(1L);
        assertTrue(ventaEliminada.isPresent());
        assertEquals("Tarjeta", ventaEliminada.get().getMetodoPago());
        verify(ventaRepository).findById(1L);
        verify(ventaRepository).delete(venta);
    }

}
