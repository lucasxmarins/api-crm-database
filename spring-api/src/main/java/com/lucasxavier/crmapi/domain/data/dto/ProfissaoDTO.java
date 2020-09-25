package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ProfissaoDTO extends RepresentationModel<ProfissaoDTO> implements Serializable {

    private static final long serialVersionUID = 8098906860713019991L;

    private Long id;
    private String nome;
    private List<Cliente> cliente;

    public ProfissaoDTO() {
    }

    public ProfissaoDTO(Long id_profissao, String nome_profissao) {
        this.id = id_profissao;
        this.nome = nome_profissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_profissao) {
        this.id = id_profissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_profissao) {
        this.nome = nome_profissao;
    }

    @JsonIgnore
    public List<Cliente> getCliente() {
        return cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfissaoDTO that = (ProfissaoDTO) o;
        return id.equals(that.id) &&
                nome.equals(that.nome) &&
                Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cliente);
    }
}
