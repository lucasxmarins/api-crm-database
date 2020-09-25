package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.DefaultConverter;
import com.lucasxavier.crmapi.domain.data.dto.PaisDTO;
import com.lucasxavier.crmapi.domain.data.models.Pais;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    private final PaisRepository repository;

    @Autowired
    public PaisService(PaisRepository repository) {
        this.repository = repository;
    }

    public List<PaisDTO> findAll() {
        return DefaultConverter.parseListObjects(repository.findAll(), PaisDTO.class);
    }

    public List<PaisDTO> findByCod(String cod) {
        var pais = repository.findByCod(cod).orElseThrow(() -> new ResourceNotFoundException(cod));
        return DefaultConverter.parseListObjects(pais, PaisDTO.class);
    }

    public PaisDTO insert(PaisDTO paisDTO) {
        try {
            var pais = DefaultConverter.parseObject(paisDTO, Pais.class);
            return DefaultConverter.parseObject(repository.save(pais), PaisDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public PaisDTO update(String cod, PaisDTO updated) {
        try {
            Pais current = repository.findByCod(cod)
                    .orElseThrow(() -> new ResourceNotFoundException(cod)).get(0);
            updateData(current, DefaultConverter.parseObject(updated, Pais.class));
            return DefaultConverter.parseObject(repository.save(current), PaisDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Pais current, Pais updated) {
        current.setNome(updated.getNome());
    }

    public void delete(String cod) {
        try {
            Pais pais = DefaultConverter.parseObject(findByCod(cod).get(0), Pais.class);
            repository.delete(pais);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
