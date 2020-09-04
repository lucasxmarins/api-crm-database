package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Montadora;
import com.lucasxavier.crmapi.domain.repositories.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class MontadoraService implements Serializable {

    @Autowired
    private MontadoraRepository repository;

    public List<Montadora> findAll(){
        return repository.findAll();
    }

    public Montadora findById(Long id){
        return repository.findById(id).get();
    }
}
