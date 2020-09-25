package com.lucasxavier.crmapi.domain.data.converters;

import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTOv2;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroRepository;
import com.lucasxavier.crmapi.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroClienteConverter {

    private final CarroRepository carroRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public CarroClienteConverter(CarroRepository carroRepository, ClienteRepository clienteRepository){
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
    }

    public CarroClienteDTOv2 convertEntityToDTO(CarroCliente carro){
        var carroDTO = new CarroClienteDTOv2();

        carroDTO.setAno(carro.getAno());
        carroDTO.setCarroId(carro.getCarro().getId());
        carroDTO.setClienteId(carro.getCliente().getId());

        return carroDTO;
    }

    public CarroCliente convertDTOToEntity(CarroClienteDTOv2 carroDTO){
        var carro = new CarroCliente();

        carro.setAno(carroDTO.getAno());
        carro.setCliente(clienteRepository.findById(carroDTO.getClienteId())
                .orElseThrow(()-> new ResourceNotFoundException(carroDTO.getClienteId())));
        carro.setCarro(carroRepository.findById(carroDTO.getCarroId())
                .orElseThrow(()-> new ResourceNotFoundException(carroDTO.getCarroId())));

        return carro;
    }

    public List<CarroClienteDTOv2> convertListEntityToDTO(List<CarroCliente> carros){
        return carros.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }


}
