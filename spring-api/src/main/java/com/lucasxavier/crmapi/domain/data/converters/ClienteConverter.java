package com.lucasxavier.crmapi.domain.data.converters;

import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
import com.lucasxavier.crmapi.domain.exceptions.ResourceNotFoundException;
import com.lucasxavier.crmapi.domain.repositories.PaisRepository;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteConverter {

    private final PaisRepository paisRepository;
    private final ProfissaoRepository profissaoRepository;

    @Autowired
    public ClienteConverter(PaisRepository paisRepository, ProfissaoRepository profissaoRepository){
        this.paisRepository = paisRepository;
        this.profissaoRepository = profissaoRepository;
    }


    public ClienteDTO convertEntityToDTO(Cliente cliente){
        var clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setPrimeiro_nome(cliente.getPrimeiro_nome());
        clienteDTO.setUltimo_nome(cliente.getUltimo_nome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setSexo(cliente.getSexo());
        clienteDTO.setCidade(cliente.getCidade());
        clienteDTO.setEmpresa(cliente.getEmpresa());
        clienteDTO.setEtnia(cliente.getEtnia());
        clienteDTO.setNascimento(cliente.getNascimento());
        clienteDTO.setPaisCod(cliente.getPais().getCodigo());
        clienteDTO.setProfissaoNome(cliente.getProfissao().getNome());

        return clienteDTO;
    }

    public Cliente convertDTOToEntity(ClienteDTO clienteDTO){
        var cliente = new Cliente();

        cliente.setId(clienteDTO.getId());
        cliente.setPrimeiro_nome(clienteDTO.getPrimeiro_nome());
        cliente.setUltimo_nome(clienteDTO.getUltimo_nome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setCidade(clienteDTO.getCidade());
        cliente.setEmpresa(clienteDTO.getEmpresa());
        cliente.setEtnia(clienteDTO.getEtnia());
        cliente.setNascimento(clienteDTO.getNascimento());
        cliente.setPais(paisRepository.findByCod(clienteDTO.getPaisCod())
                .orElseThrow(()-> new ResourceNotFoundException(clienteDTO.getPaisCod())).get(0));
        cliente.setProfissao(profissaoRepository.findProfissaoByNome(clienteDTO.getProfissaoNome())
                .orElseThrow(()-> new ResourceNotFoundException(clienteDTO.getProfissaoNome())).get(0));

        return cliente;
    }

    public List<ClienteDTO> convertListClientesToDTO(List<Cliente> clientes){
        return clientes.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

}
