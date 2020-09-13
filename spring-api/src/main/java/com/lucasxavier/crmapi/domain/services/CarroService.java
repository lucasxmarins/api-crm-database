package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.models.CarroVO;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class CarroService implements Serializable {

    private final CarroRepository repository;

    @Autowired
    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<CarroVO> findAll() {
        return repository.findAll();
    }

    public CarroVO findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public CarroVO insert(CarroVO carro) {
        try {
            return repository.save(carro);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public CarroVO update(Long id, CarroVO updated) {
        try {
            CarroVO current = repository.getOne(id);
            updateData(current, updated);
            return repository.save(current);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    private void updateData(CarroVO current, CarroVO update) {
        current.setNome(update.getNome());
        current.setMontadora(update.getMontadora());
    }
}
