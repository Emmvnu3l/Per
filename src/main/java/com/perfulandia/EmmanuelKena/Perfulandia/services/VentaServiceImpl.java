package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository ventaRepository;

   @Override
    @Transactional(readOnly = true)
    public List<Venta> findByAll() { 
        return (List<Venta>) ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findById(Long id) {
       return ventaRepository.findById(id);
    }

    @Override
    @Transactional
    public Venta crearVenta(Venta unVenta) {
     return ventaRepository.save(unVenta);
    }

    @Override
    @Transactional
    public Optional<Venta> delete(Long id) {
        Optional<Venta> VentaOptional = ventaRepository.findById(id);
        VentaOptional.ifPresent(ventaRepository::delete);
        return VentaOptional;
    }
  }
