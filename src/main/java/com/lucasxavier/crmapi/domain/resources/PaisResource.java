package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.entities.Pais;
import com.lucasxavier.crmapi.domain.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/paises")
public class PaisResource implements Serializable {

    private final PaisService service;

    @Autowired
    public PaisResource(PaisService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{cod}")
    public ResponseEntity<List<Pais>> findById(@PathVariable String cod){
        return ResponseEntity.ok().body(service.findByCod(cod));
    }
}
