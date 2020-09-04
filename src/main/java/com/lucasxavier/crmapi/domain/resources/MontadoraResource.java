package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.entities.Montadora;
import com.lucasxavier.crmapi.domain.services.MontadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/montadoras")
public class MontadoraResource implements Serializable {

    private final MontadoraService service;

    @Autowired
    public MontadoraResource(MontadoraService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Montadora>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Montadora> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
