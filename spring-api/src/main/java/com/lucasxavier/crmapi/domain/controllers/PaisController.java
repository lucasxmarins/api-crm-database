package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.PaisDTO;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import com.lucasxavier.crmapi.domain.services.PaisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(value = "Pais", tags = {"Pais Endpoint"})
@RestController
@RequestMapping(value = "paises")
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

    @ApiOperation(value ="Get list of all Countries", tags = {"Pais"})
    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<PaisDTO>> findAll() {
        var paises = service.findAll();
        paises.forEach(pais -> pais.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaisController.class)
                        .findByCod(pais.getCodigo())).withSelfRel()));

        return ResponseEntity.ok().body(paises);
    }

    @ApiOperation(value ="Get Country", tags = {"Pais"})
    @GetMapping(value = "/{cod}", produces = {"application/json", "application/xml"})
    public ResponseEntity<PaisDTO> findByCod(@PathVariable String cod) {
        var pais = service.findByCod(cod).get(0);
        linkGenerator.createPaisLinks(pais);

        return ResponseEntity.ok().body(pais);
    }

    @ApiOperation(value ="Add new Country", tags = {"Pais"})
    @PostMapping(produces = {"application/json", "application/xml"},
                 consumes = {"application/json", "application/xml"})
    public ResponseEntity<PaisDTO> insert(@RequestBody PaisDTO pais) {
        pais = service.insert(pais);
        linkGenerator.createPaisLinks(pais);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cod}").buildAndExpand(pais.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(pais);
    }

    @ApiOperation(value ="Update Country", tags = {"Pais"})
    @PutMapping(value = "/{cod}",
                produces = {"application/json", "application/xml"},
                consumes = {"application/json", "application/xml"})
    public ResponseEntity<PaisDTO> update(@PathVariable String cod, @RequestBody PaisDTO pais) {
        pais = service.update(cod, pais);
        linkGenerator.createPaisLinks(pais);

        return ResponseEntity.ok().body(pais);
    }

    @ApiOperation(value ="Delete Country", tags = {"Pais"})
    @DeleteMapping(value = "/{cod}")
    public ResponseEntity<PaisDTO> delete(@PathVariable String cod) {
        service.delete(cod);
        return ResponseEntity.noContent().build();

    }

    // Associated Getter ----------------------------------------------------------------------------------------
    @ApiOperation(value ="Get all Customers from Country", tags = {"Pais", "Cliente"})
    @GetMapping(value = "/{cod}/clientes", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<ClienteDTO>> findAllClientesByPais(@PathVariable String cod){
        var clientes = clienteService.findAllClientesByPaisCod(cod);
        clientes.forEach(cliente -> cliente.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(ClienteController.class).findById(cliente.getId())).withSelfRel()));

        return ResponseEntity.ok().body(clientes);
    }
}
