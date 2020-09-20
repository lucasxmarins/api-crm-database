package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.converters.DozerConverter;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroClienteRepository;
import com.lucasxavier.crmapi.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroClienteService {

    private final CarroClienteRepository repository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CarroClienteService(CarroClienteRepository repository, ClienteRepository clienteRepository){
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public CarroClienteDTO addCarToClient(Long id, CarroCliente carro) {
        carro.setCliente(clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
        try {
            return DozerConverter.parseObject(repository.save(carro), CarroClienteDTO.class);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<CarroClienteDTO> findCarsByClient(Long id){
        List<CarroCliente> carros = repository.findCarByClient(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return DozerConverter.parseListObjects(carros, CarroClienteDTO.class);
    }

    public List<CarroClienteDTO> findAllClientCars(Long id) {
        List<CarroCliente> carros = repository.findAllClientCars(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return DozerConverter.parseListObjects(carros, CarroClienteDTO.class);
    }
}



