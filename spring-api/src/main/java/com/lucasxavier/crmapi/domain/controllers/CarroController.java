package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
import com.lucasxavier.crmapi.domain.services.CarroClienteService;
import com.lucasxavier.crmapi.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final LinkGenerator linkGenerator;

    @Autowired
    public CarroController(CarroService service, CarroClienteService carroClienteService, LinkGenerator linkGenerator) {
        this.service = service;
        this.carroClienteService = carroClienteService;
        this.linkGenerator = linkGenerator;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // V2
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        CarroDTO carro = service.findByIdV2(id);
        linkGenerator.createCarroLinks(carro);
        return ResponseEntity.ok().body(carro);
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<CarroDTO>> findAll() {
        List<CarroDTO> carros = service.findAllV2();

        carros.forEach((carro)->
            carro.add(linkTo(methodOn(CarroController.class).findById(carro.getId())).withSelfRel()));

        return ResponseEntity.ok().body(carros);
    }

    @PostMapping(produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTO> insert(@RequestBody CarroDTO carro) {
        carro = service.insertV2(carro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();
        linkGenerator.createCarroLinks(carro);
        return ResponseEntity.created(uri).body(carro);
    }

    @PutMapping(value = "/{id}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody CarroDTO carro) {
        carro = service.updateV2(id, carro);
        linkGenerator.createCarroLinks(carro);
        return ResponseEntity.ok().body(carro);
    }

    // Associated method -----------------------------------------------------------------------------------

    @GetMapping(value = "/{id}/clientes", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<CarroClienteDTO>> findAllClientCars(@PathVariable Long id){
        var carros = carroClienteService.findAllCarrosFromClientesByCarroId(id);
        carros.forEach(carro -> carro.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
                        .findCarroFromClienteById(carro.getClienteId(), carro.getCarroId())).withSelfRel()));

        return ResponseEntity.ok().body(carros);
    }

}
