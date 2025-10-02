package com.perfulandia.EmmanuelKena.Perfulandia.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.CuponRepository;

@Service
public class CuponServiceImpl implements CuponService {

    @Autowired
    private CuponRepository cuponRepository;
 
   @Override
    @Transactional(readOnly = true)
    public List<Cupon> findByAll() { 
        return (List<Cupon>) cuponRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cupon> findById(Long id) {
       return cuponRepository.findById(id);
    }

    @Override
    @Transactional
    public Cupon crearCupon(Cupon unCupon) {
     return cuponRepository.save(unCupon);
    }

    @Override
    @Transactional
    public Optional<Cupon> delete(Long id) {
        Optional<Cupon> CuponOptional = cuponRepository.findById(id);
        CuponOptional.ifPresent(cuponRepository::delete);
        return CuponOptional;
    }
  }
