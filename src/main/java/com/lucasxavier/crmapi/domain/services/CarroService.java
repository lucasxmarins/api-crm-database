package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Carro;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CarroService implements Serializable {

    private final CarroRepository repository;

    @Autowired
    public CarroService(CarroRepository repository){
        this.repository = repository;
    }

    public List<Carro> findAll() {
        return repository.findAll();
    }

    public Carro findById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }
}
