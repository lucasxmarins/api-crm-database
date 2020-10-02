package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.entities.Cliente;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

public class PaisDTO extends RepresentationModel<PaisDTO> implements Serializable {

    private static final long serialVersionUID = 5231511094360653004L;

    private String codigo;
    private String nome;
    private List<Cliente> clientes;

    public PaisDTO() {
    }

    public PaisDTO(String codigo_pais, String nome_pais) {
        this.codigo = codigo_pais;
        this.nome = nome_pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String cod_pais) {
        this.codigo = cod_pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_pais) {
        this.nome = nome_pais;
    }

    @JsonIgnore
    public List<Cliente> getClientes() {
        return clientes;
    }


}
