package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
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
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO cliente) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok().body(service.update(id, cliente));
    }

    // Associated VERBS
    @GetMapping(value = "/{id}/carros")
    public  ResponseEntity<List<CarroClienteDTO>> findCarsPerClient(@PathVariable Long id){
        return ResponseEntity.ok().body(carroClienteService.findCarsByClient(id));
    }

    @PostMapping(value = "/{id}/carros")
    public ResponseEntity<CarroClienteDTO> addCarToClient(@PathVariable Long id , @RequestBody CarroCliente carro){
        return ResponseEntity.ok().body(carroClienteService.addCarToClient(id, carro));
    }


}
