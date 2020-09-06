package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.entities.Montadora;
import com.lucasxavier.crmapi.domain.services.MontadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/montadoras")
public class MontadoraResource implements Serializable {

    private final MontadoraService service;

    @Autowired
    public MontadoraResource(MontadoraService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Montadora>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Montadora> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Montadora> insert(@RequestBody Montadora montadora) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(montadora.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(montadora));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Montadora> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Montadora> update(@PathVariable Long id, @RequestBody Montadora montadora){
        return ResponseEntity.ok().body(service.update(id, montadora));
    }
}