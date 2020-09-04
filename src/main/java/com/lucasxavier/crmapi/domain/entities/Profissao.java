package com.lucasxavier.crmapi.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "profissao")
public class Profissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profissao;
    private String nome_profissao;

    public Profissao(){}

    public Profissao(Long id_profissao, String nome_profissao) {
        this.id_profissao = id_profissao;
        this.nome_profissao = nome_profissao;
    }

    public Long getId_profissao() {
        return id_profissao;
    }

    public void setId_profissao(Long id_profissao) {
        this.id_profissao = id_profissao;
    }

    public String getNome_profissao() {
        return nome_profissao;
    }

    public void setNome_profissao(String nome_profissao) {
        this.nome_profissao = nome_profissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissao profissao = (Profissao) o;
        return Objects.equals(id_profissao, profissao.id_profissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_profissao);
    }
}
