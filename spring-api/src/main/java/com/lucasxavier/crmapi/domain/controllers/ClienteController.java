package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.services.CarroClienteService;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private final LinkGenerator linkGenerator;

    @Autowired
    public ClienteController(ClienteService service, CarroClienteService carroClienteService,
                             LinkGenerator linkGenerator) {
        this.service = service;
        this.carroClienteService = carroClienteService;
        this.linkGenerator = linkGenerator;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        var clientesDTO = service.findAll();
        clientesDTO.forEach((clienteDTO)->
                clienteDTO.add(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ClienteController.class)
                        .findById(clienteDTO.getId())).withSelfRel()));
        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        var clienteDTO = service.findById(id);
        linkGenerator.createClienteLinks(clienteDTO);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO cliente) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        var clienteDTO = service.insert(cliente);
        linkGenerator.createClienteLinks(clienteDTO);
        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        var clienteDTO = service.update(id, cliente);
        linkGenerator.createClienteLinks(clienteDTO);
        return ResponseEntity.ok().body(clienteDTO);
    }

    // CarroClient Methods ---------------------------------------------------------------------------
    @GetMapping(value = "/{id}/carros")
    public  ResponseEntity<List<CarroClienteDTO>> findAllCarrosFromClient(@PathVariable Long id){
        var carros = carroClienteService.findAllCarrosFromClient(id);
        carros.forEach((carro)->
                carro.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class)
                .findCarroFromClienteById(id, carro.getCarroId())).withSelfRel()));

        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> findCarroFromClienteById(@PathVariable Long id, @PathVariable Long carroId){
        var carroDTO = carroClienteService.findCarroFromClienteById(id, carroId);
        linkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PostMapping(value = "/{id}/carros")
    public ResponseEntity<CarroClienteDTO> addCarroToCliente(@PathVariable Long id , @RequestBody CarroClienteDTO carro){
        var carroDTO = carroClienteService.addCarroToCliente(id, carro);
        linkGenerator.createCarroClienteLinks(carro);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PutMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> updateCarroFromCliente(@PathVariable Long id, @PathVariable Long carroId,
                                                                  @RequestBody CarroClienteDTO carro){
        var carroDTO = carroClienteService.updateCarroFromCliente(id, carroId, carro);
        linkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @DeleteMapping(value = "/{id}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> deleteCarroFromClienteById(@PathVariable Long id, @PathVariable Long carroId){
        carroClienteService.deleteCarroFromCliente(id, carroId);
        return ResponseEntity.noContent().build();
    }

}
