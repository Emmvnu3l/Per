package com.perfulandia.EmmanuelKena.Perfulandia.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.CuponRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays; //ES EL ARRAY.ASLIST 
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponServiceImpl cuponServiceImpl;

    private Cupon cupon;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        cupon = new Cupon();
        cupon.setId(1L);
        cupon.setCodigo("VERANO10");;
    }


    //Método findByAll
    @Test
    void findByAllCuponTest(){
        List<Cupon> cupones = Arrays.asList(cupon);
        when(cuponRepository.findAll()).thenReturn(cupones);
        List<Cupon> resultado = cuponServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(cuponRepository).findAll();

    }
    



    //Método findbyid
    @Test
    void finByCuponTest(){
        when(cuponRepository.findById(1L)).thenReturn(Optional.of(cupon));
        Optional<Cupon> resultado = cuponServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("VERANO10", resultado.get().getCodigo());
        verify(cuponRepository).findById(1L);
    }

    //Método save
    @Test
    void saveCuponId(){
        when(cuponRepository.save(any(Cupon.class))).thenReturn(cupon);
        Cupon saved = cuponServiceImpl.crearCupon(cupon);
        assertNotNull(saved);
        assertEquals("VERANO10", saved.getCodigo());
        verify(cuponRepository).save(cupon);

    }

    //Método modificar
    @Test
    void modificarCuponTest(){
        Cupon cuponModificado = new Cupon();
        cuponModificado.setId(1L);
        cuponModificado.setCodigo("VERANO25");

        when(cuponRepository.save(any(Cupon.class))).thenReturn(cuponModificado);
        Cupon resultado = cuponServiceImpl.crearCupon(cuponModificado);
        assertNotNull(resultado);
        assertEquals("VERANO25", resultado.getCodigo());
        verify(cuponRepository).save(cuponModificado);
        
    }

    //Método Eliminar
    @Test 
    void deleteCuponTest(){
        when(cuponRepository.findById(1L)).thenReturn(Optional.of(cupon));
        Optional<Cupon> cuponEliminado = cuponServiceImpl.delete(1L);
        assertTrue(cuponEliminado.isPresent());
        assertEquals("VERANO10", cuponEliminado.get().getCodigo());
        verify(cuponRepository).findById(1L);
        verify(cuponRepository).delete(cupon);
    }

}
