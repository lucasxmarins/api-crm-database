package com.lucasxavier.crmapi.domain.data.converters;

import com.lucasxavier.crmapi.domain.data.dto.ClienteDTO;
import com.lucasxavier.crmapi.domain.data.entities.Cliente;
import com.lucasxavier.crmapi.domain.data.entities.Pais;
import com.lucasxavier.crmapi.domain.data.entities.Profissao;
import com.lucasxavier.crmapi.domain.repositories.PaisRepository;
import com.lucasxavier.crmapi.domain.repositories.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteConverter {

    private final PaisRepository paisRepository;
    private final ProfissaoRepository profissaoRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
        clienteDTO.setNascimento(sdf.format(cliente.getNascimento().getTime()));

        Pais pais = cliente.getPais();
        Profissao profissao = cliente.getProfissao();

        if (pais != null) {
            clienteDTO.setPaisCod(pais.getCodigo());
        }
        if(profissao != null) {
            clienteDTO.setProfissaoNome(profissao.getNome());
        }

        return clienteDTO;
    }

    public Cliente convertDTOToEntity(ClienteDTO clienteDTO) throws ParseException {
        var cliente = new Cliente();

        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(sdf.parse(clienteDTO.getNascimento()));

        cliente.setId(clienteDTO.getId());
        cliente.setPrimeiro_nome(clienteDTO.getPrimeiro_nome());
        cliente.setUltimo_nome(clienteDTO.getUltimo_nome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSexo(clienteDTO.getSexo());
        cliente.setCidade(clienteDTO.getCidade());
        cliente.setEmpresa(clienteDTO.getEmpresa());
        cliente.setEtnia(clienteDTO.getEtnia());
        cliente.setNascimento(nascimento);

        paisRepository.findByCod(clienteDTO.getPaisCod())
                .ifPresent(pais -> cliente.setPais(pais.get(0)));

        profissaoRepository.findProfissaoByNome(clienteDTO.getProfissaoNome())
                .ifPresent(profissao -> cliente.setProfissao(profissao.get(0)));

        return cliente;
    }

    public List<ClienteDTO> convertListClientesToDTO(List<Cliente> clientes){
        return clientes.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

}
