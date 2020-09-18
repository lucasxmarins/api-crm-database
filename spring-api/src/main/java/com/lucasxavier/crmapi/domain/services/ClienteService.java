package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.converters.DozerConverter;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
import com.lucasxavier.crmapi.domain.data.models.Pais;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), ClienteDTO.class);
    }

    public ClienteDTO findById(Long id) {
        var cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return DozerConverter.parseObject(cliente, ClienteDTO.class);
    }

    public ClienteDTO insert(ClienteDTO clienteDTO) {
        try {
            var cliente = DozerConverter.parseObject(clienteDTO, Cliente.class);
            return DozerConverter.parseObject(repository.save(cliente), ClienteDTO.class);
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

    public ClienteDTO update(Long id, ClienteDTO updated) {
        try {
            Cliente current = repository.getOne(id);
            updateData(current, DozerConverter.parseObject(updated, Cliente.class));
            return DozerConverter.parseObject(repository.save(current), ClienteDTO.class);
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

    public List<ClienteDTO> findClientsPerCountry(String cod){
        return DozerConverter.parseListObjects(repository.findAllClients(cod), ClienteDTO.class);
    }

}
