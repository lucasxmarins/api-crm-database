package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.entities.Pais;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PaisService implements Serializable {

    private final PaisRepository repository;

    @Autowired
    public PaisService(PaisRepository repository){
        this.repository = repository;
    }

    public List<Pais> findAll(){
        return repository.findAll();
    }

    public List<Pais> findByCod(String cod){
        return repository.findByCod(cod).orElseThrow(()-> new ResourceNotFoundException(cod));
    }

    public Pais insert(Pais pais){
        try {
            return repository.save(pais);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Pais update(String cod, Pais updated){
        try {
            Pais current = repository.findByCod(cod)
                    .orElseThrow(()-> new ResourceNotFoundException(cod)).get(0);
            updateData(current, updated);
            return repository.save(current);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Pais current, Pais updated){
        current.setNome(updated.getNome());
    }

    public void delete(String cod){
        try {
            Pais pais = findByCod(cod).get(0);
            repository.delete(pais);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
