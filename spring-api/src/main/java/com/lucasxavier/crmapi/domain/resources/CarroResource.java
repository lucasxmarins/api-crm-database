package com.lucasxavier.crmapi.domain.resources;

import com.lucasxavier.crmapi.domain.data.models.Carro;
import com.lucasxavier.crmapi.domain.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/carros")
public class CarroResource implements Serializable {

    private final CarroService service;

    @Autowired
    public CarroResource(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> findAll() {

        List<Carro> carros = service.findAll();
        /*
        for (CarroVO carro : carros) {
            carro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarroResource.class)
                    .findById(carro.getId())).withSelfRel());
        }
        */
        return ResponseEntity.ok().body(carros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) {

        Carro carro = service.findById(id);
        //carro.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarroResource.class)
        //        .findById(carro.getId())).withSelfRel());

        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> insert(@RequestBody Carro carro) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(carro));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Carro> insert(@PathVariable Long id, @RequestBody Carro carro) {
        return ResponseEntity.ok().body(service.update(id, carro));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Carro> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
