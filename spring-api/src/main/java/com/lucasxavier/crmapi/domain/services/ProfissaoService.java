package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.models.Profissao;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

@Service
public class ProfissaoService implements Serializable {

    private final ProfissaoRepository repository;

    @Autowired
    public ProfissaoService(ProfissaoRepository repository) {
        this.repository = repository;
    }

    public List<Profissao> findAll() {
        return repository.findAll();
    }

    public Profissao findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Profissao insert(Profissao profissao) {
        try {
            return repository.save(profissao);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Profissao update(Long id, Profissao updated) {
        try {
            Profissao current = repository.getOne(id);
            updateData(current, updated);
            return repository.save(current);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Profissao current, Profissao updated) {
        current.setNome(updated.getNome());
    }
}
