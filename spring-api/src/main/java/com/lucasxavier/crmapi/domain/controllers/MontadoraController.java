package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.dto.CarroDTO;
import com.lucasxavier.crmapi.domain.data.dto.MontadoraDTO;
import com.lucasxavier.crmapi.domain.services.CarroService;
import com.lucasxavier.crmapi.domain.services.MontadoraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MontadoraController(MontadoraService service, CarroService carroService) {
        this.service = service;
        this.carroService = carroService;
    }

    @GetMapping
    public ResponseEntity<List<MontadoraDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MontadoraDTO> insert(@RequestBody MontadoraDTO montadora) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(montadora.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(montadora));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MontadoraDTO> update(@PathVariable Long id, @RequestBody MontadoraDTO montadora) {
        return ResponseEntity.ok().body(service.update(id, montadora));
    }

    // Associated Getter
    @GetMapping(value = "/{id}/carros")
    public ResponseEntity<List<CarroDTO>> getCarsPerConstructor(@PathVariable long id){
        return ResponseEntity.ok().body(carroService.findCarsByManufacturer(id));
    }



}