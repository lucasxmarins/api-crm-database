package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.ClienteConverter;
import com.lucasxavier.crmapi.domain.data.converters.DefaultConverter;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
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

    public List<ClienteDTO> findAll() {
        return converter.convertListClientesToDTO(repository.findAll());
    }

    public ClienteDTO findById(Long id) {
        var cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return converter.convertEntityToDTO(cliente);
    }

    public ClienteDTO insert(ClienteDTO clienteDTO) {
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

    public ClienteDTO update(Long id, ClienteDTO updated) {
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

    // Associated Method - PaisController
    public List<ClienteDTO> findAllClientesByPaisCod(String cod){
        var clientesDTO = repository.findAllClientesByPaisCod(cod)
                .orElseThrow(()-> new ResourceNotFoundException(cod));
        return converter.convertListClientesToDTO(clientesDTO);
    }

    // Associated Method - ProfissaoController
    public List<ClienteDTO> findAllClientesByProfissaoId(Long id){
        var clientesDTO = repository.findAllClientesByProfissaoId(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
        return converter.convertListClientesToDTO(clientesDTO);
    }

    // Associated Method - MontadoraController
    public List<ClienteDTO> findAllClientesByMontadoraId(Long id){
        var clientesDTO = repository.findAllClientesByMontadoraId(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
        return converter.convertListClientesToDTO(clientesDTO);
    }

}
