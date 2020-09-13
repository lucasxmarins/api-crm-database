package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.data.models.Pais;
import com.lucasxavier.crmapi.domain.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/paises")
public class PaisResource implements Serializable {

    private final PaisService service;

    @Autowired
    public PaisResource(PaisService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{cod}")
    public ResponseEntity<List<Pais>> findById(@PathVariable String cod) {
        return ResponseEntity.ok().body(service.findByCod(cod));
    }

    @PostMapping
    public ResponseEntity<Pais> insert(@RequestBody Pais pais) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cod}").buildAndExpand(pais.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(service.insert(pais));

        //IdentifierGenerationException
    }

    @PutMapping(value = "/{cod}")
    public ResponseEntity<Pais> update(@PathVariable String cod, @RequestBody Pais pais) {
        return ResponseEntity.ok().body(service.update(cod, pais));
    }

    @DeleteMapping(value = "/{cod}")
    public ResponseEntity<Pais> delete(@PathVariable String cod) {
        service.delete(cod);
        return ResponseEntity.noContent().build();

    }
}
