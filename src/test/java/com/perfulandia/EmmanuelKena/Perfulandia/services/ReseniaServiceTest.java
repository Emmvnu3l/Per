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

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ReseniaRepository;

@SpringBootTest
public class ReseniaServiceTest {



    @Mock
    private ReseniaRepository reseniaRepository;

    @InjectMocks
    private ReseniaServiceImpl reseniaServiceImpl;

    private Resenia resenia;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        resenia = new Resenia();
        resenia.setId(1L);
        resenia.setCalificacion(10);;
    }


    //Método findByAll
    @Test
    void finByAllReseniaTest(){
        List<Resenia> resenias = Arrays.asList(resenia);
        when(reseniaRepository.findAll()).thenReturn(resenias);
        List<Resenia> resultado = reseniaServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(reseniaRepository).findAll();

    }


    //Método findbyid
    @Test
    void finByReseniaTest(){
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(resenia));
        Optional<Resenia> resultado = reseniaServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(10, resultado.get().getCalificacion());
        verify(reseniaRepository).findById(1L);
    }

    //Método save
    @Test
    void guardarReseniaId(){
        when(reseniaRepository.save(any(Resenia.class))).thenReturn(resenia);
        Resenia saved = reseniaServiceImpl.crearResenia(resenia);
        assertNotNull(saved);
        assertEquals(10, saved.getCalificacion());
        verify(reseniaRepository).save(resenia);

    }

    //Método modificar
    @Test
    void modificarReseniaTest(){
        Resenia reseniaModificada = new Resenia();
        reseniaModificada.setId(1L);
        reseniaModificada.setCalificacion(9);;

        when(reseniaRepository.save(any(Resenia.class))).thenReturn(reseniaModificada);
        Resenia resultado = reseniaServiceImpl.crearResenia(reseniaModificada);
        assertNotNull(resultado);
        assertEquals(9, resultado.getCalificacion());
        verify(reseniaRepository).save(reseniaModificada);
        
    }

    //Método Eliminar
    @Test 
    void eliminarReseniaTest(){
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(resenia));
        Optional<Resenia> reseniaEliminada = reseniaServiceImpl.delete(1L);
        assertTrue(reseniaEliminada.isPresent());
        assertEquals(10, reseniaEliminada.get().getCalificacion());
        verify(reseniaRepository).findById(1L);
        verify(reseniaRepository).delete(resenia);
    }

}
