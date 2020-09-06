package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Montadora;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class MontadoraService implements Serializable {

    private final MontadoraRepository repository;

    @Autowired
    public MontadoraService(MontadoraRepository repository){
        this.repository = repository;
    }

    public List<Montadora> findAll(){
        return repository.findAll();
    }

    public Montadora findById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Montadora insert(Montadora montadora) {
        try {
            return repository.save(montadora);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public Montadora update(Long id,Montadora updated){
        try {
            Montadora current = repository.getOne(id);
            updateData(current, updated);
            return repository.save(current);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Montadora current, Montadora updated) {
        current.setNome(updated.getNome());
    }


}
