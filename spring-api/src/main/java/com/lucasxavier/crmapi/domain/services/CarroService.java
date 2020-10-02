package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.CarroConverter;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
import com.lucasxavier.crmapi.domain.data.entities.Carro;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository repository;
    private final CarroConverter converter;

    @Autowired
    public CarroService(CarroRepository repository, CarroConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    // N apagar
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    private void updateData(Carro current, Carro update) {
        current.setNome(update.getNome());
        current.setMontadora(update.getMontadora());
    }

    // V2

    public CarroDTO findByIdV2(Long id) {
        var carro = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return converter.convertEntityToDTO(carro);
    }

    public List<CarroDTO> findAllV2() {
        return converter.convertListEntityToDTO(repository.findAll());
    }

    public CarroDTO insertV2(CarroDTO carroDTO) {
        try {
            var carro = converter.convertDTOToEntity(carroDTO);
            return converter.convertEntityToDTO(repository.save(carro));

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public CarroDTO updateV2(Long id, CarroDTO updated) {
        try {
            Carro current = repository.getOne(id);
            updateData(current, converter.convertDTOToEntity(updated));

            return converter.convertEntityToDTO(repository.save(current));

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    // Associated method - MontadoraController
    public List<CarroDTO> findAllCarrosByMontadoraId(Long id){
        List<Carro> carros = repository.findCarsByManufacturer(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
        return converter.convertListEntityToDTO(carros);
    }
}
