package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.PaisDTO;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import com.lucasxavier.crmapi.domain.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/paises")
public class PaisController {

    private final PaisService service;
    private final ClienteService clienteService;

    @Autowired
    public PaisController(PaisService service, ClienteService clienteService) {
        this.service = service;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<PaisDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{cod}")
    public ResponseEntity<List<PaisDTO>> findById(@PathVariable String cod) {
        return ResponseEntity.ok().body(service.findByCod(cod));
    }

    @PostMapping
    public ResponseEntity<PaisDTO> insert(@RequestBody PaisDTO pais) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cod}").buildAndExpand(pais.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(service.insert(pais));

        //IdentifierGenerationException
    }

    @PutMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> update(@PathVariable String cod, @RequestBody PaisDTO pais) {
        return ResponseEntity.ok().body(service.update(cod, pais));
    }

    @DeleteMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> delete(@PathVariable String cod) {
        service.delete(cod);
        return ResponseEntity.noContent().build();

    }

    // Associated methods
    @GetMapping(value = "/{cod}/clientes")
    public ResponseEntity<List<ClienteDTO>> getClientes(@PathVariable String cod){
        return ResponseEntity.ok().body(clienteService.findClientsPerCountry(cod));
    }
}
