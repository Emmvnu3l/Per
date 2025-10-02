package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;

public interface ProveedorService {
    List<Proveedor> findByAll();

    Optional<Proveedor> findById(Long id);

    Proveedor crearProveedor(Proveedor unProveedor);

    Optional<Proveedor> delete(Long id);

}
