package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.ClienteConverter;
import com.lucasxavier.crmapi.domain.data.converters.DefaultConverter;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTOv2;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
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
    private final ClienteConverter converter;

    @Autowired
    public ClienteService(ClienteRepository repository, ClienteConverter converter){
        this.repository = repository;
        this.converter = converter;
    }

    public List<ClienteDTOv2> findAll() {
        return converter.convertListClientesToDTO(repository.findAll());
    }

    public ClienteDTOv2 findById(Long id) {
        var cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return converter.convertEntityToDTO(cliente);
    }

    public ClienteDTOv2 insert(ClienteDTOv2 clienteDTO) {
        try {
            var cliente = DefaultConverter.parseObject(clienteDTO, Cliente.class);
            return converter.convertEntityToDTO(repository.save(cliente));

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

    public ClienteDTOv2 update(Long id, ClienteDTOv2 updated) {
        try {
            Cliente current = repository.getOne(id);
            updateData(current, converter.convertDTOToEntity(updated));
            return converter.convertEntityToDTO(repository.save(current));

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

    // Associated Methods
    public List<ClienteDTO> findClientsPerCountry(String cod){
        return DefaultConverter.parseListObjects(repository.findClientsPerCountry(cod), ClienteDTO.class);
    }

    public List<ClienteDTO> findClientsPerJob(Long id){
        return DefaultConverter.parseListObjects(repository.findClientsPerJob(id), ClienteDTO.class);
    }

}
