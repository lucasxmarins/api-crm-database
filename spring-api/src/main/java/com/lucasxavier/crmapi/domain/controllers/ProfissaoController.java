package com.lucasxavier.crmapi.domain.controllers;

import com.lucasxavier.crmapi.domain.controllers.linkGenerator.LinkGenerator;
import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.dto.ProfissaoDTO;
import com.lucasxavier.crmapi.domain.services.ClienteService;
import com.lucasxavier.crmapi.domain.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/profissoes")
public class ProfissaoController {

    private final ProfissaoService service;
    private final ClienteService clienteService;
    private final LinkGenerator linkGenerator;

    @Autowired
    public ProfissaoController(ProfissaoService service, ClienteService clienteService, LinkGenerator linkGenerator) {
        this.service = service;
        this.clienteService = clienteService;
        this.linkGenerator = linkGenerator;
    }

    @GetMapping
    public ResponseEntity<List<ProfissaoDTO>> findAll() {
        var profissoes = service.findAll();
        profissoes.forEach((profissaoDTO)->
                profissaoDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfissaoController.class)
                        .findById(profissaoDTO.getId())).withSelfRel()));

        return ResponseEntity.ok().body(profissoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> findById(@PathVariable Long id) {
        var profissao = service.findById(id);
        linkGenerator.createProfissaoLinks(profissao);
        return ResponseEntity.ok().body(profissao);
    }

    @PostMapping
    public ResponseEntity<ProfissaoDTO> insert(@RequestBody ProfissaoDTO profissao) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(profissao.getId()).toUri();
        profissao = service.insert(profissao);
        linkGenerator.createProfissaoLinks(profissao);
        return ResponseEntity.created(uri).body(profissao);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> update(@PathVariable Long id, @RequestBody ProfissaoDTO profissao) {
        profissao = service.update(id, profissao);
        linkGenerator.createProfissaoLinks(profissao);

        return ResponseEntity.ok().body(profissao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProfissaoDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Associated Getter
    @GetMapping(value = "/{id}/clientes")
    public ResponseEntity<List<ClienteDTO>> findAllClientesByProfissao(@PathVariable long id){
        var clientes = clienteService.findAllClientesByProfissaoId(id);
        clientes.forEach(cliente -> cliente.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ClienteController.class).findById(cliente.getId())).withSelfRel()));

        return ResponseEntity.ok().body(clientes);
    }



}
