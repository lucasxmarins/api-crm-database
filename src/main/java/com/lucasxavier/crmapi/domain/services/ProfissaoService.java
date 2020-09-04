package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Profissao;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ProfissaoService implements Serializable {

    private final ProfissaoRepository repository;

    @Autowired
    public ProfissaoService(ProfissaoRepository repository){
        this.repository = repository;
    }

    public List<Profissao> findAll(){
        return repository.findAll();
    }

    public Profissao findById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }
}
