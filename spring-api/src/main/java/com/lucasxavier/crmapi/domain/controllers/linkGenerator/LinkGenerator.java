package com.lucasxavier.crmapi.domain.controllers.linkGenerator;

import com.lucasxavier.crmapi.domain.controllers.CarroController;
import com.lucasxavier.crmapi.domain.controllers.ClienteController;
import com.lucasxavier.crmapi.domain.controllers.MontadoraController;
import com.lucasxavier.crmapi.domain.data.dto.CarroClienteDTOv2;
import com.lucasxavier.crmapi.domain.data.dto.CarroDTOv2;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTOv2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class LinkGenerator {

    public static void createCarroLinks(CarroDTOv2 carro){
        carro.add(linkTo(methodOn(CarroController.class).findById(carro.getId())).withSelfRel());
        carro.add(linkTo(methodOn(MontadoraController.class).findById(carro.getMontadoraId())).withRel("montadora"));
        carro.add(linkTo(methodOn(CarroController.class).findAllClientCars(carro.getId())).withRel("clientes"));
    }
    
    public static void createCarroClienteLinks(CarroClienteDTOv2 carroCliente){
        carroCliente.add(linkTo(methodOn(ClienteController.class)
                .findCarroFromClienteById(carroCliente.getClienteId(), carroCliente.getCarroId())).withSelfRel());
        carroCliente.add(linkTo(methodOn(ClienteController.class)
                .findById(carroCliente.getClienteId())).withRel("cliente"));
        carroCliente.add(linkTo(methodOn(CarroController.class)
                .findById(carroCliente.getCarroId())).withRel("carro"));
        carroCliente.add(linkTo(methodOn(ClienteController.class)
                .findAllCarrosFromClient(carroCliente.getClienteId())).withRel("cliente_carros"));
    }

    public static void createClienteLinks(ClienteDTOv2 cliente){

        cliente.add();

    }
}
