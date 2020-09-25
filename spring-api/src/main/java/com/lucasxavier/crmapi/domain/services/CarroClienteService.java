package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.CarroClienteConverter;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroClienteService {

    private final CarroClienteRepository repository;
    private final CarroClienteConverter converter;

    @Autowired
    public CarroClienteService(CarroClienteRepository repository, CarroClienteConverter converter){
        this.repository = repository;
        this.converter = converter;
    }

    // ClienteController
    public CarroClienteDTO addCarroToCliente(Long id, CarroClienteDTO carroDTO) {
        try {
            carroDTO.setClienteId(id);
            var carro = converter.convertDTOToEntity(carroDTO);
            return converter.convertEntityToDTO(repository.save(carro));
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    // ClienteController
    public List<CarroClienteDTO> findAllCarrosFromClient(Long id){
        List<CarroCliente> carros = repository.findCarByClient(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return converter.convertListEntityToDTO(carros);
    }

    // ClienteController
    public CarroClienteDTO findCarroFromClienteById(Long clientId, Long carroId){
        List<CarroCliente> carros = repository.finsClientCarById(clientId, carroId)
                .orElseThrow(()-> new ResourceNotFoundException(carroId));
        return converter.convertEntityToDTO(carros.get(0));
    }

    // ClienteController
    public void deleteCarroFromCliente(Long clientId, Long carroId){
        try{
            var carro = findCarroFromClienteById(clientId, carroId);
            repository.delete(converter.convertDTOToEntity(carro));
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(carroId);
        }
    }

    // ClienteController
    public CarroClienteDTO updateCarroFromCliente(Long clientId, Long carroId, CarroClienteDTO updated){
        var carroDTO = findCarroFromClienteById(clientId, carroId);
        carroDTO.setAno(updated.getAno());
        var carro = converter.convertDTOToEntity(carroDTO);

        return converter.convertEntityToDTO(repository.save(carro));
    }

    // CarroController
    public List<CarroClienteDTO> findAllCarrosFromClientesByCarroId(Long id) {
        List<CarroCliente> carros = repository.findAllClientCars(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return converter.convertListEntityToDTO(carros);
    }
}



