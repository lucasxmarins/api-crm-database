package com.lucasxavier.crmapi.domain.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "montadora")
public class Montadora implements Serializable {

    private static final long serialVersionUID = -3925455975257073207L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_montadora")
    private Long id;
    @Column(name = "nome_montadora")
    private String nome;

    @OneToMany(mappedBy = "montadora")
    private List<Carro> carros;

    public Montadora() {
    }

    public Montadora(Long id_montadora, String nome_montadora) {
        this.id = id_montadora;
        this.nome = nome_montadora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_montadora) {
        this.id = id_montadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_montadora) {
        this.nome = nome_montadora;
    }

    @JsonIgnore
    public List<Carro> getCarros() {
        return carros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Montadora montadora = (Montadora) o;
        return Objects.equals(id, montadora.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
