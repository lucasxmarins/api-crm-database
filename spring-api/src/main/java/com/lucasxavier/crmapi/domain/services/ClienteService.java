package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.models.Cliente;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ClienteService implements Serializable {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente cliente) {
        try {
            return repository.save(cliente);
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

    public Cliente update(Long id, Cliente updated) {
        try {
            Cliente current = repository.getOne(id);
            updateData(current, updated);
            return repository.save(current);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Cliente current, Cliente updated) {
        current.setCidade(updated.getCidade());
        current.setEmail(updated.getEmail());
        current.setEtnia(updated.getEtnia());
        current.setNascimento(updated.getNascimento());
        current.setPais(updated.getPais());
        current.setPrimeiro_nome(updated.getPrimeiro_nome());
        current.setUltimo_nome(updated.getUltimo_nome());
        current.setProfissao(updated.getProfissao());
        current.setSexo(updated.getSexo());
        current.setEmpresa(updated.getEmpresa());
    }

}
