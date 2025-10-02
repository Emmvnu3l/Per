package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    
    @Autowired
    private ProductoRepository productoRepository;

   @Override
    @Transactional(readOnly = true)
    public List<Producto> findByAll() { 
        return (List<Producto>) productoRepository.findAll();
    }

    /*Encontrar por id */
    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
       return productoRepository.findById(id);
    }

    /*Crear Producto */
    @Override
    @Transactional
    public Producto crearProducto(Producto unProducto) {
     return productoRepository.save(unProducto);
    }

    /*Eliminar Producto */
    @Override
    @Transactional
    public Optional<Producto> delete(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        productoOptional.ifPresent(productoRepository::delete);
        return productoOptional;
    }
  }
    

