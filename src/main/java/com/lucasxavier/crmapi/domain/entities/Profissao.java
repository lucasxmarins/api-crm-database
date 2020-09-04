package com.lucasxavier.crmapi.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "profissao")
public class Profissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profissao")
    private Long id;
    @Column(name = "nome_profissao")
    private String nome;

    public Profissao(){}

    public Profissao(Long id_profissao, String nome_profissao) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissao profissao = (Profissao) o;
        return Objects.equals(id, profissao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
