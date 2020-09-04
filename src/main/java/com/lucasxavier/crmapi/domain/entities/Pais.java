package com.lucasxavier.crmapi.domain.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    @Id
    @Column(name = "codigo_pais")
    private String codigo;
    @Column(name = "nome_pais")
    private String nome;

    public Pais(){}

    public Pais(String codigo_pais, String nome_pais) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(codigo, pais.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
