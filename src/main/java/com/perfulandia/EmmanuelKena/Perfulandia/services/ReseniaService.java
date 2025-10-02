package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;

public interface ReseniaService {
        List<Resenia> findByAll();

        Optional<Resenia> findById(Long id);

        Resenia crearResenia(Resenia unResenia);

        Optional<Resenia> delete(Long id);
}
