package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.DefaultConverter;
import com.lucasxavier.crmapi.domain.data.dto.MontadoraDTO;
import com.lucasxavier.crmapi.domain.data.entities.Montadora;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MontadoraService {

    private final MontadoraRepository repository;

    @Autowired
    public MontadoraService(MontadoraRepository repository) {
        this.repository = repository;
    }

    public List<MontadoraDTO> findAll() {
        return DefaultConverter.parseListObjects(repository.findAll(), MontadoraDTO.class);
    }

    public MontadoraDTO findById(Long id) {
        var montadora = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return DefaultConverter.parseObject(montadora, MontadoraDTO.class);
    }

    public MontadoraDTO insert(MontadoraDTO montadoraDTO) {
        try {
            var montadora = DefaultConverter.parseObject(montadoraDTO, Montadora.class);
            return DefaultConverter.parseObject(repository.save(montadora), MontadoraDTO.class);

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

    public MontadoraDTO update(Long id, MontadoraDTO updated) {
        try {
            Montadora current = repository.getOne(id);
            updateData(current, DefaultConverter.parseObject(updated, Montadora.class));
            return DefaultConverter.parseObject(repository.save(current), MontadoraDTO.class);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Montadora current, Montadora updated) {
        current.setNome(updated.getNome());
    }


}
