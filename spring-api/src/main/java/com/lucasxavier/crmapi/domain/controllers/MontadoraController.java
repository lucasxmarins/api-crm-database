package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.MontadoraDTO;
import com.lucasxavier.crmapi.domain.services.CarroService;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import com.lucasxavier.crmapi.domain.services.MontadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/montadoras")
public class MontadoraController {

    private final MontadoraService service;
    private final CarroService carroService;
    private final ClienteService clienteService;
    private final LinkGenerator linkGenerator;

    @Autowired
    public MontadoraController(MontadoraService service, CarroService carroService, ClienteService clienteService,
                               LinkGenerator linkGenerator) {
        this.service = service;
        this.carroService = carroService;
        this.clienteService = clienteService;
        this.linkGenerator = linkGenerator;
    }

    @GetMapping
    public ResponseEntity<List<MontadoraDTO>> findAll() {
        var montadorasDTO = service.findAll();
        montadorasDTO.forEach(montadoraDTO ->
                montadoraDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MontadoraController.class)
                .findById(montadoraDTO.getId())).withSelfRel()));
        return ResponseEntity.ok().body(montadorasDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> findById(@PathVariable Long id) {
        var montadoraDTO = service.findById(id);
        linkGenerator.createMontadoraLinks(montadoraDTO);

        return ResponseEntity.ok().body(montadoraDTO);
    }

    @PostMapping
    public ResponseEntity<MontadoraDTO> insert(@RequestBody MontadoraDTO montadora) {
        montadora = service.insert(montadora);
        linkGenerator.createMontadoraLinks(montadora);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(montadora.getId()).toUri();

        return ResponseEntity.created(uri).body(montadora);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> update(@PathVariable Long id, @RequestBody MontadoraDTO montadora) {
        var montadoraDTO = service.update(id, montadora);
        linkGenerator.createMontadoraLinks(montadoraDTO);

        return ResponseEntity.ok().body(montadoraDTO);
    }

    // Associated Getter
    @GetMapping(value = "/{id}/carros")
    public ResponseEntity<List<CarroDTO>> findAllCarrosByMontadoraId(@PathVariable long id){
        var carrosDTO = carroService.findAllCarrosByMontadoraId(id);
        carrosDTO.forEach(carro ->
            carro.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(CarroController.class)
                    .findById(carro.getId())).withSelfRel()));

        return ResponseEntity.ok().body(carrosDTO);
    }

    @GetMapping(value = "/{id}/clientes")
    public ResponseEntity<List<ClienteDTO>> findAllClientesByMontadoraId(@PathVariable long id){
        var clientesDTO = clienteService.findAllClientesByMontadoraId(id);
        clientesDTO.forEach(carro ->
                carro.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ClienteController.class)
                                .findById(carro.getId())).withSelfRel()));

        return ResponseEntity.ok().body(clientesDTO);
    }



}