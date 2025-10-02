package com.perfulandia.EmmanuelKena.Perfulandia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProveedorRepository;

@SpringBootTest
public class ProveedorServiceTest {


    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorServiceImpl;

    private Proveedor proveedor;

    @BeforeEach
    void Inicio(){
        MockitoAnnotations.openMocks(this);
        proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Distribuidora Fragancias SA");
    }

    //Método findByAll
    @Test
    void findByAllProveedorTest(){
        List<Proveedor> proveedores = Arrays.asList(proveedor);
        when(proveedorRepository.findAll()).thenReturn(proveedores);
        List<Proveedor> resultado = proveedorServiceImpl.findByAll();
        assertEquals(1, resultado.size());
        verify(proveedorRepository).findAll();
        
    }

    //Método findbyId
    @Test
    void findByIdProveedoresTest(){
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));
        Optional<Proveedor> resultado = proveedorServiceImpl.findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Distribuidora Fragancias SA", resultado.get().getNombre());
        verify(proveedorRepository).findById(1L);

    }
      

    //Método save
    @Test
    void guardarProveedorTest(){
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);
        Proveedor saved = proveedorServiceImpl.crearProveedor(proveedor);
        assertNotNull(saved);
        assertEquals("Distribuidora Fragancias SA", saved.getNombre());
        verify(proveedorRepository).save(proveedor);
    }


 //Método modificar
    @Test
    void modificarProveedorTest(){
        Proveedor proveedorModificado = new Proveedor();
        proveedorModificado.setId(1L);
        proveedorModificado.setNombre("Distribuidora Fragancias Nuevas SA");

        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorModificado);
        Proveedor resultado = proveedorServiceImpl.crearProveedor(proveedorModificado);
        assertNotNull(resultado);
        assertEquals("Distribuidora Fragancias Nuevas SA", resultado.getNombre());
        verify(proveedorRepository).save(proveedorModificado);
    }


    //Método Eliminar
@Test
void eliminarProveedor() {
    when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));
    Optional<Proveedor> proveedorEliminado = proveedorServiceImpl.delete(1L);
    assertTrue(proveedorEliminado.isPresent());
    assertEquals("Distribuidora Fragancias SA", proveedorEliminado.get().getNombre());
    verify(proveedorRepository).findById(1L);
    verify(proveedorRepository).delete(proveedor);
}

}
