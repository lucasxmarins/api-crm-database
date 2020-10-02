package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.data.converters.DefaultConverter;
import com.lucasxavier.crmapi.domain.data.entities.Profissao;
import com.lucasxavier.crmapi.domain.data.dto.ProfissaoDTO;
import com.lucasxavier.crmapi.domain.exceptions.DatabaseException;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProfissaoService {

    private final ProfissaoRepository repository;

    @Autowired
    public ProfissaoService(ProfissaoRepository repository) {
        this.repository = repository;
    }

    public List<ProfissaoDTO> findAll() {
        return DefaultConverter.parseListObjects(repository.findAll(), ProfissaoDTO.class);
    }

    public ProfissaoDTO findById(Long id) {
        var profissao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return DefaultConverter.parseObject(profissao, ProfissaoDTO.class);
    }

    public ProfissaoDTO insert(ProfissaoDTO profissaoVO) {
        try {

            var profissao = DefaultConverter.parseObject(profissaoVO, Profissao.class);
            return DefaultConverter.parseObject(repository.save(profissao), ProfissaoDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ProfissaoDTO update(Long id, ProfissaoDTO updated) {
        try {
            Profissao current = repository.getOne(id);
            updateData(current, DefaultConverter.parseObject(updated, Profissao.class));
            return DefaultConverter.parseObject(repository.save(current), ProfissaoDTO.class);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Profissao current, Profissao updated) {
        current.setNome(updated.getNome());
    }

}
