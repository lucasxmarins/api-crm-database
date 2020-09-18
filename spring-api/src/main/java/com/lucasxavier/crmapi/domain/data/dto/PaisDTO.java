package com.lucasxavier.crmapi.domain.data.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
import java.util.List;

public class PaisDTO {

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
