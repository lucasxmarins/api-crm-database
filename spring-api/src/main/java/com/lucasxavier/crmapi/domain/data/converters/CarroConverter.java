package com.lucasxavier.crmapi.domain.data.converters;

import com.lucasxavier.crmapi.domain.data.dto.CarroDTOv2;
import com.lucasxavier.crmapi.domain.data.models.Carro;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.MontadoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroConverter {

    private final MontadoraRepository montadoraRepository;

    public CarroConverter(MontadoraRepository montadoraRepository){
        this.montadoraRepository = montadoraRepository;
    }

    public CarroDTOv2 convertEntityToDTO(Carro carro){
        CarroDTOv2 carroDTO = new CarroDTOv2();

        carroDTO.setId(carro.getId());
        carroDTO.setNome(carro.getNome());
        carroDTO.setMontadoraId(carro.getMontadora().getId());

        return carroDTO;
    }

    public Carro convertDTOToEntity(CarroDTOv2 carroDTO){
        Carro carro = new Carro();

        carro.setId(carroDTO.getId());
        carro.setNome(carroDTO.getNome());
        Long montadoraId = carroDTO.getMontadoraId();
        carro.setMontadora(montadoraRepository.findById(montadoraId)
                .orElseThrow(()-> new ResourceNotFoundException(montadoraId)));
        return carro;
    }

    public List<CarroDTOv2> convertListEntityToDTO(List<Carro> carros){
        return carros.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

}
