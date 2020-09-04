package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Pais;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PaisService implements Serializable {

    @Autowired
    private PaisRepository repository;

    public List<Pais> findAll(){
        return repository.findAll();
    }

    public List<Pais> findByCod(String cod){
        return repository.findByCod(cod).orElseThrow(()-> new ResourceNotFoundException(cod));
    }
}
