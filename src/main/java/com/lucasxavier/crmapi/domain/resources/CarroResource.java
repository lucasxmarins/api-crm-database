package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.entities.Carro;
import com.lucasxavier.crmapi.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/carros")
public class CarroResource implements Serializable {

    private final CarroService service;

    @Autowired
    public CarroResource(CarroService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }


}
