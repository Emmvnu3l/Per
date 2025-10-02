package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;

public interface VentaService {
    List<Venta> findByAll();

    Optional<Venta> findById(Long id);

    Venta crearVenta(Venta unVenta);

    Optional<Venta> delete(Long id);
}
