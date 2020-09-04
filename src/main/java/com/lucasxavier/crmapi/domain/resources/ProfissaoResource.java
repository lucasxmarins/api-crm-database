package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.entities.Profissao;
import com.lucasxavier.crmapi.domain.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/profissoes")
public class ProfissaoResource {

    @Autowired
    private ProfissaoService service;

    @GetMapping
    public ResponseEntity<List<Profissao>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Profissao> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
