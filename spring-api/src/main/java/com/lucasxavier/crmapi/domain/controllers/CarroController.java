package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTOv2;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTOv2;
import com.lucasxavier.crmapi.domain.services.CarroClienteService;
import com.lucasxavier.crmapi.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/carros")
public class CarroController{

    private final CarroService service;
    private final CarroClienteService carroClienteService;

    @Autowired
    public CarroController(CarroService service, CarroClienteService carroClienteService) {
        this.service = service;
        this.carroClienteService = carroClienteService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroDTOv2> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // V2
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTOv2> findById(@PathVariable Long id) {
        CarroDTOv2 carro = service.findByIdV2(id);
        LinkGenerator.createCarroLinks(carro);
        return ResponseEntity.ok().body(carro);
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<CarroDTOv2>> findAll() {
        List<CarroDTOv2> carros = service.findAllV2();

        carros.forEach((carro)->
            carro.add(linkTo(methodOn(CarroController.class).findById(carro.getId())).withSelfRel()));

        return ResponseEntity.ok().body(carros);
    }

    @PostMapping(produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTOv2> insert(@RequestBody CarroDTOv2 carro) {
        carro = service.insertV2(carro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();
        LinkGenerator.createCarroLinks(carro);
        return ResponseEntity.created(uri).body(carro);
    }

    @PutMapping(value = "/{id}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTOv2> update(@PathVariable Long id, @RequestBody CarroDTOv2 carro) {
        carro = service.updateV2(id, carro);
        LinkGenerator.createCarroLinks(carro);
        return ResponseEntity.ok().body(carro);
    }

    // Associated VERBS (ADICIONAR V2) - CarroClienteService
    @GetMapping(value = "/{id}/clientes", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<CarroClienteDTOv2>> findAllClientCars(@PathVariable Long id){
        return ResponseEntity.ok().body(carroClienteService.findAllCarrosFromClientesByCarroId(id));
    }

}
