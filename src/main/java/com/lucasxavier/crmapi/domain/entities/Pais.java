package com.lucasxavier.crmapi.domain.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    @Id
    private String codigo_pais;
    private String nome_pais;

    public Pais(){}

    public Pais(String codigo_pais, String nome_pais) {
        this.codigo_pais = codigo_pais;
        this.nome_pais = nome_pais;
    }

    public String getCodigo_pais() {
        return codigo_pais;
    }

    public void setCodigo_pais(String cod_pais) {
        this.codigo_pais = cod_pais;
    }

    public String getNome_pais() {
        return nome_pais;
    }

    public void setNome_pais(String nome_pais) {
        this.nome_pais = nome_pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(codigo_pais, pais.codigo_pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo_pais);
    }
}
