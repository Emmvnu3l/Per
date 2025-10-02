package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;

public interface CuponService {
        List<Cupon> findByAll();

        Optional<Cupon> findById(Long id);

        Cupon crearCupon(Cupon unCupon);

        Optional<Cupon> delete(Long id);
}
