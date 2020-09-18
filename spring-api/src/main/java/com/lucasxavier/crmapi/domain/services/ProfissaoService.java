package com.lucasxavier.crmapi.domain.services;

import com.lucasxavier.crmapi.domain.converters.DozerConverter;
import com.lucasxavier.crmapi.domain.data.models.Profissao;
import com.lucasxavier.crmapi.domain.data.vo.ProfissaoVO;
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

    public List<ProfissaoVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), ProfissaoVO.class);
    }

    public ProfissaoVO findById(Long id) {
        var profissao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return DozerConverter.parseObject(profissao, ProfissaoVO.class);
    }

    public ProfissaoVO insert(ProfissaoVO profissaoVO) {
        try {

            var profissao = DozerConverter.parseObject(profissaoVO, Profissao.class);
            return DozerConverter.parseObject(repository.save(profissao), ProfissaoVO.class);

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

    public ProfissaoVO update(Long id, ProfissaoVO updated) {
        try {
            Profissao current = repository.getOne(id);
            updateData(current, DozerConverter.parseObject(updated, Profissao.class));
            return DozerConverter.parseObject(repository.save(current), ProfissaoVO.class);
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
