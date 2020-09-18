package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.models.Profissao;
import com.lucasxavier.crmapi.domain.data.vo.ProfissaoVO;
import com.lucasxavier.crmapi.domain.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/profissoes")
public class ProfissaoController {

    private final ProfissaoService service;

    @Autowired
    public ProfissaoController(ProfissaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfissaoVO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissaoVO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfissaoVO> insert(@RequestBody ProfissaoVO profissao) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profissao.getId()).toUri(); //java.net.uri
        return ResponseEntity.created(uri).body(service.insert(profissao));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfissaoVO> update(@PathVariable Long id, @RequestBody ProfissaoVO profissao) {
        return ResponseEntity.ok().body(service.update(id, profissao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProfissaoVO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
