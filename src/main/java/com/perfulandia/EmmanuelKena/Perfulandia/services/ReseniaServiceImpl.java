package com.perfulandia.EmmanuelKena.Perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;
import com.perfulandia.EmmanuelKena.Perfulandia.repositories.ReseniaRepository;


@Service
public class ReseniaServiceImpl implements ReseniaService{

    @Autowired
    private ReseniaRepository reseniaRepository;

   @Override
    @Transactional(readOnly = true)
    public List<Resenia> findByAll() { 
        return (List<Resenia>) reseniaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resenia> findById(Long id) {
       return reseniaRepository.findById(id);
    }

    @Override
    @Transactional
    public Resenia crearResenia(Resenia unResenia) {
     return reseniaRepository.save(unResenia);
    }

    @Override
    @Transactional
    public Optional<Resenia> delete(Long id) {
        Optional<Resenia> ReseniaOptional = reseniaRepository.findById(id);
        ReseniaOptional.ifPresent(reseniaRepository::delete);
        return ReseniaOptional;
    }
  }
