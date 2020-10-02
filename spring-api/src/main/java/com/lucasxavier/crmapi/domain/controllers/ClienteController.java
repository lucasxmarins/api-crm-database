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
@CrossOrigin
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

    @GetMapping(value = "/{clienteId}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long clienteId) {
        var clienteDTO = service.findById(clienteId);
        linkGenerator.createClienteLinks(clienteDTO);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO cliente) {
        cliente = service.insert(cliente);
        linkGenerator.createClienteLinks(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @DeleteMapping(value = "/{clienteId}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long clienteId) {
        service.delete(clienteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{clienteId}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long clienteId, @RequestBody ClienteDTO cliente) {
        var clienteDTO = service.update(clienteId, cliente);
        linkGenerator.createClienteLinks(clienteDTO);
        return ResponseEntity.ok().body(clienteDTO);
    }

    // CarroClient Methods ---------------------------------------------------------------------------
    @GetMapping(value = "/{clienteId}/carros")
    public  ResponseEntity<List<CarroClienteDTO>> findAllCarrosFromClient(@PathVariable Long clienteId){
        var carros = carroClienteService.findAllCarrosFromClient(clienteId);
        carros.forEach((carro)->
                carro.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class)
                .findCarroFromClienteById(clienteId, carro.getCarroId())).withSelfRel()));

        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{clienteId}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> findCarroFromClienteById(@PathVariable Long clienteId, @PathVariable Long carroId){
        var carroDTO = carroClienteService.findCarroFromClienteById(clienteId, carroId);
        linkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PostMapping(value = "/{clienteId}/carros")
    public ResponseEntity<CarroClienteDTO> addCarroToCliente(@PathVariable Long clienteId , @RequestBody CarroClienteDTO carro){
        carro = carroClienteService.addCarroToCliente(clienteId, carro);
        linkGenerator.createCarroClienteLinks(carro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{carroId}").buildAndExpand(carro.getCarroId()).toUri();

        return ResponseEntity.created(uri).body(carro);
    }

    @PutMapping(value = "/{clienteId}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> updateCarroFromCliente(@PathVariable Long clienteId, @PathVariable Long carroId,
                                                                  @RequestBody CarroClienteDTO carro){
        var carroDTO = carroClienteService.updateCarroFromCliente(clienteId, carroId, carro);
        linkGenerator.createCarroClienteLinks(carroDTO);
        return ResponseEntity.ok().body(carroDTO);
    }

    @DeleteMapping(value = "/{clienteId}/carros/{carroId}")
    public ResponseEntity<CarroClienteDTO> deleteCarroFromClienteById(@PathVariable Long clienteId, @PathVariable Long carroId){
        carroClienteService.deleteCarroFromCliente(clienteId, carroId);
        return ResponseEntity.noContent().build();
    }

}
