package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.converters.DozerConverter;
import com.lucasxavier.crmapi.domain.data.models.Carro;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
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

    @Autowired
    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<CarroDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), CarroDTO.class);
    }

    public CarroDTO findById(Long id) {
        var carro = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return DozerConverter.parseObject(carro, CarroDTO.class);
    }

    public CarroDTO insert(CarroDTO carroVO) {
        try {
            var carro = DozerConverter.parseObject(carroVO, Carro.class);
            return DozerConverter.parseObject(repository.save(carro), CarroDTO.class);

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

    public CarroDTO update(Long id, CarroDTO updated) {
        try {
            Carro current = repository.getOne(id);
            updateData(current, DozerConverter.parseObject(updated, Carro.class));

            return DozerConverter.parseObject(repository.save(current), CarroDTO.class);
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
}
