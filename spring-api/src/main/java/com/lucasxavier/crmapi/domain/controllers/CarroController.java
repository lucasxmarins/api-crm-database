package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
import com.lucasxavier.crmapi.domain.services.CarroClienteService;
import com.lucasxavier.crmapi.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CarroDTO>> findAll() {

        List<CarroDTO> carros = service.findAll();
        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        CarroDTO carro = service.findById(id);
        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<CarroDTO> insert(@RequestBody CarroDTO carro) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(carro));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> insert(@PathVariable Long id, @RequestBody CarroDTO carro) {
        return ResponseEntity.ok().body(service.update(id, carro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Associated VERBS
    @GetMapping(value = "/{id}/clientes")
    public ResponseEntity<List<CarroClienteDTO>> findAllClientCars(@PathVariable Long id){
        return ResponseEntity.ok().body(carroClienteService.findAllClientCars(id));
    }

}
