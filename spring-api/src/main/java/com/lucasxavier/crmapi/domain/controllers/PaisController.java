package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.PaisDTO;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import com.lucasxavier.crmapi.domain.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final LinkGenerator linkGenerator;

    @Autowired
    public PaisController(PaisService service, ClienteService clienteService, LinkGenerator linkGenerator) {
        this.service = service;
        this.clienteService = clienteService;
        this.linkGenerator = linkGenerator;
    }

    @GetMapping
    public ResponseEntity<List<PaisDTO>> findAll() {
        var paises = service.findAll();
        paises.forEach(pais -> pais.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaisController.class)
                        .findByCod(pais.getCodigo())).withSelfRel()));

        return ResponseEntity.ok().body(paises);
    }

    @GetMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> findByCod(@PathVariable String cod) {
        var pais = service.findByCod(cod).get(0);
        linkGenerator.createPaisLinks(pais);

        return ResponseEntity.ok().body(pais);
    }

    @PostMapping
    public ResponseEntity<PaisDTO> insert(@RequestBody PaisDTO pais) {
        pais = service.insert(pais);
        linkGenerator.createPaisLinks(pais);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cod}").buildAndExpand(pais.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(pais);
    }

    @PutMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> update(@PathVariable String cod, @RequestBody PaisDTO pais) {
        pais = service.update(cod, pais);
        linkGenerator.createPaisLinks(pais);

        return ResponseEntity.ok().body(pais);
    }

    @DeleteMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> delete(@PathVariable String cod) {
        service.delete(cod);
        return ResponseEntity.noContent().build();

    }

    // Associated Getter
    @GetMapping(value = "/{cod}/clientes")
    public ResponseEntity<List<ClienteDTO>> findAllClientesByPais(@PathVariable String cod){
        var clientes = clienteService.findAllClientesByPaisCod(cod);
        clientes.forEach(cliente -> cliente.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(ClienteController.class).findById(cliente.getId())).withSelfRel()));

        return ResponseEntity.ok().body(clientes);
    }
}
