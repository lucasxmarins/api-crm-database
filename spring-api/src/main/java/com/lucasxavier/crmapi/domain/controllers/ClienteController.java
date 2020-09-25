package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTOv2;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTOv2;
import com.lucasxavier.crmapi.domain.services.CarroClienteService;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteController{

    private final ClienteService service;
    private final CarroClienteService carroClienteService;

    @Autowired
    public ClienteController(ClienteService service, CarroClienteService carroClienteService) {
        this.service = service;
        this.carroClienteService = carroClienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTOv2>> findAll() {
        var clientes = service.findAll();
        /* clientes.forEach((cliente)->
                cliente.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ClienteController.class)
                        .findById(cliente.getId())).withSelfRel())); */
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTOv2> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTOv2> insert(@RequestBody ClienteDTOv2 cliente) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTOv2> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTOv2> update(@PathVariable Long id, @RequestBody ClienteDTOv2 cliente) {
        return ResponseEntity.ok().body(service.update(id, cliente));
    }

    // CarroClient Methods
    @GetMapping(value = "/{id}/carros")
    public  ResponseEntity<List<CarroClienteDTOv2>> findAllCarrosFromClient(@PathVariable Long id){
        var carros = carroClienteService.findAllCarrosFromClient(id);
        /*carros.forEach((carro)->
                carro.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class)
                .findCarroFromClienteById(id, carro.getCarroId())).withSelfRel())); */

        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTOv2> findCarroFromClienteById(@PathVariable Long id, @PathVariable Long carroId){
        var carroDTO = carroClienteService.findCarroFromClienteById(id, carroId);
        //LinkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PostMapping(value = "/{id}/carros")
    public ResponseEntity<CarroClienteDTOv2> addCarroToCliente(@PathVariable Long id , @RequestBody CarroClienteDTOv2 carro){
        var carroDTO = carroClienteService.addCarroToCliente(id, carro);
        //LinkGenerator.createCarroClienteLinks(carro);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PutMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTOv2> updateCarroFromCliente(@PathVariable Long id, @PathVariable Long carroId,
                                                             @RequestBody CarroClienteDTOv2 carro){
        var carroDTO = carroClienteService.updateCarroFromCliente(id, carroId, carro);
        //LinkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @DeleteMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTOv2> deleteCarroFromClienteById(@PathVariable Long id, @PathVariable Long carroId){
        carroClienteService.deleteCarroFromCliente(id, carroId);
        return ResponseEntity.noContent().build();
    }






}
