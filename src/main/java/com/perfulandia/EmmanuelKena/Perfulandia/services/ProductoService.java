package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;

public interface ProductoService {
    List<Producto> findByAll();

    Optional<Producto> findById(Long id);

    Producto crearProducto(Producto unProducto);

    Optional<Producto> delete(Long id);
}
