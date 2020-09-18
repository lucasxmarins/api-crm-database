package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.converters.DozerConverter;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.ProfissaoDTO;
import com.lucasxavier.crmapi.domain.services.ClienteService;
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
    private final ClienteService clienteService;

    @Autowired
    public ProfissaoController(ProfissaoService service, ClienteService clienteService) {
        this.service = service;
        this.clienteService = clienteService;
    }

    // Basic CRUD
    @GetMapping
    public ResponseEntity<List<ProfissaoDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProfissaoDTO> insert(@RequestBody ProfissaoDTO profissao) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profissao.getId()).toUri(); //java.net.uri
        return ResponseEntity.created(uri).body(service.insert(profissao));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> update(@PathVariable Long id, @RequestBody ProfissaoDTO profissao) {
        return ResponseEntity.ok().body(service.update(id, profissao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Associated Getter
    @GetMapping(value = "/{id}/clientes")
    public ResponseEntity<List<ClienteDTO>> findClientsPerJob(@PathVariable long id){
        return ResponseEntity.ok().body(clienteService.findClientsPerJob(id));
    }



}
