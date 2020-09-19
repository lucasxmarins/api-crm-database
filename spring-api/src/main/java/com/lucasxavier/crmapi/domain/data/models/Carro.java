package com.lucasxavier.crmapi.domain.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "carro_montadora")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carro")
    private Long id;
    @Column(name = "nome_carro")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_montadora")
    private Montadora montadora;

    @JsonIgnore
    @OneToMany(mappedBy = "id.carro")
    private Set<CarroCliente> carroClienteSet = new HashSet<>();

    public Carro() {
    }

    public Carro(Long id_carro, String nome_carro, Montadora montadora) {
        this.id = id_carro;
        this.nome = nome_carro;
        this.montadora = montadora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_carro) {
        this.id = id_carro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_carro) {
        this.nome = nome_carro;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    @JsonIgnore
    public Set<CarroCliente> getCarroClienteSet() {
        return carroClienteSet;
    }

    public void setCarroClienteSet(Set<CarroCliente> carroClienteSet) {
        this.carroClienteSet = carroClienteSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
