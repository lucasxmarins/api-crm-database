package com.lucasxavier.crmapi.domain.controllers.linkGenerator;

import com.lucasxavier.crmapi.domain.controllers.*;
import com.lucasxavier.crmapi.domain.data.dto.*;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LinkGenerator {

    private final ProfissaoRepository profissaoRepository;

    @Autowired
    public LinkGenerator(ProfissaoRepository profissaoRepository){
        this.profissaoRepository = profissaoRepository;
    }

    public void createCarroLinks(CarroDTO carro){
        carro.add(linkTo(methodOn(CarroController.class)
                .findById(carro.getId())).withSelfRel());
        carro.add(linkTo(methodOn(MontadoraController.class)
                .findById(carro.getMontadoraId())).withRel("montadora"));
        carro.add(linkTo(methodOn(CarroController.class)
                .findAllCarrosFromClientes(carro.getId())).withRel("clientes"));
    }
    
    public void createCarroClienteLinks(CarroClienteDTO carroCliente){
        carroCliente.add(linkTo(methodOn(ClienteController.class)
                .findCarroFromClienteById(carroCliente.getClienteId(), carroCliente.getCarroId()))
                .withSelfRel());
        carroCliente.add(linkTo(methodOn(ClienteController.class)
                .findById(carroCliente.getClienteId())).withRel("cliente"));
        carroCliente.add(linkTo(methodOn(CarroController.class)
                .findById(carroCliente.getCarroId())).withRel("modelo"));
    }

    public void createClienteLinks(ClienteDTO cliente){

        cliente.add(linkTo(methodOn(ClienteController.class)
                .findById(cliente.getId())).withSelfRel());
        cliente.add(linkTo(methodOn(ClienteController.class)
                .findAllCarrosFromClient(cliente.getId())).withRel("carros"));


        if(cliente.getPaisCod() != null) {
            cliente.add(linkTo(methodOn(PaisController.class)
                    .findByCod(cliente.getPaisCod())).withRel("pais"));
        }

        if(cliente.getProfissaoNome() != null) {
            Long profissaoId = profissaoRepository.findProfissaoByNome(cliente.getProfissaoNome())
                    .orElseThrow(() -> new ResourceNotFoundException(cliente.getProfissaoNome()))
                    .get(0).getId();

            cliente.add(linkTo(methodOn(ProfissaoController.class)
                    .findById(profissaoId)).withRel("profissao"));
        }
    }

    public void createMontadoraLinks(MontadoraDTO montadora){

        montadora.add(linkTo(methodOn(MontadoraController.class)
                .findById(montadora.getId())).withSelfRel());
        montadora.add(linkTo(methodOn(MontadoraController.class)
                .findAllCarrosByMontadoraId(montadora.getId())).withRel("carros"));
        montadora.add(linkTo(methodOn(MontadoraController.class)
                .findAllClientesByMontadoraId(montadora.getId())).withRel("clientes"));
    }

    public void createProfissaoLinks(ProfissaoDTO profissao){

        profissao.add(linkTo(methodOn(ProfissaoController.class)
                .findById(profissao.getId())).withSelfRel());
        profissao.add(linkTo(methodOn(ProfissaoController.class)
                .findAllClientesByProfissao(profissao.getId())).withRel("clientes"));
    }

    public void createPaisLinks(PaisDTO pais){

        pais.add(linkTo(methodOn(PaisController.class).findByCod(pais.getCodigo())).withSelfRel());
        pais.add(linkTo(methodOn(PaisController.class)
                .findAllClientesByPais(pais.getCodigo())).withRel("clientes"));

    }

}
