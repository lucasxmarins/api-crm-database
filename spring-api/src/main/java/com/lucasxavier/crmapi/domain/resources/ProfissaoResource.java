package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.data.models.Profissao;
import com.lucasxavier.crmapi.domain.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/profissoes")
public class ProfissaoResource {

    private final ProfissaoService service;

    @Autowired
    public ProfissaoResource(ProfissaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Profissao>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Profissao> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Profissao> insert(@RequestBody Profissao profissao) {
        service.insert(profissao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profissao.getId()).toUri(); //java.net.uri
        return ResponseEntity.created(uri).body(profissao);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Profissao> update(@PathVariable Long id, @RequestBody Profissao profissao) {
        return ResponseEntity.ok().body(service.update(id, profissao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Profissao> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
