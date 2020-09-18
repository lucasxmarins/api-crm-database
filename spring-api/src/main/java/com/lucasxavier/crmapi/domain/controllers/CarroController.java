package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.data.vo.CarroVO;
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

    @Autowired
    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CarroVO>> findAll() {

        List<CarroVO> carros = service.findAll();
        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroVO> findById(@PathVariable Long id) {
        CarroVO carro = service.findById(id);
        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<CarroVO> insert(@RequestBody CarroVO carro) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(carro));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarroVO> insert(@PathVariable Long id, @RequestBody CarroVO carro) {
        return ResponseEntity.ok().body(service.update(id, carro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroVO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
