package com.lucasxavier.crmapi.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "montadora")
public class Montadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_montadora;
    private String nome_montadora;

    public Montadora(){}

    public Montadora(Long id_montadora, String nome_montadora) {
        this.id_montadora = id_montadora;
        this.nome_montadora = nome_montadora;
    }

    public Long getId_montadora() {
        return id_montadora;
    }

    public void setId_montadora(Long id_montadora) {
        this.id_montadora = id_montadora;
    }

    public String getNome_montadora() {
        return nome_montadora;
    }

    public void setNome_montadora(String nome_montadora) {
        this.nome_montadora = nome_montadora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Montadora montadora = (Montadora) o;
        return Objects.equals(id_montadora, montadora.id_montadora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_montadora);
    }
}
