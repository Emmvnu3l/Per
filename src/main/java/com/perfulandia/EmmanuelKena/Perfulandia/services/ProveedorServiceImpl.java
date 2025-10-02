package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    private ProveedorRepository ProveedorRepository;

   @Override
    @Transactional(readOnly = true)
    public List<Proveedor> findByAll() { 
        return (List<Proveedor>) ProveedorRepository.findAll();
    }

    /*Encontrar por id */
    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> findById(Long id) {
       return ProveedorRepository.findById(id);
    }

    /*Crear Proveedor */
    @Override
    @Transactional
    public Proveedor crearProveedor(Proveedor unProveedor) {
     return ProveedorRepository.save(unProveedor);
    }

    /*Eliminar Proveedor */
    @Override
    @Transactional
    public Optional<Proveedor> delete(Long id) {
        Optional<Proveedor> proveedorOptional = ProveedorRepository.findById(id);
        proveedorOptional.ifPresent(ProveedorRepository::delete);
        return proveedorOptional;
    }
  }

