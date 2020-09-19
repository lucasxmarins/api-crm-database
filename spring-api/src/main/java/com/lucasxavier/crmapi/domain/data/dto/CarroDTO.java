package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import com.lucasxavier.crmapi.domain.data.models.Montadora;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CarroDTO implements Serializable {
    private static final long serialVersionUID = -6488625267004062221L;

    private Long id;
    private String nome;
    private Montadora montadora;
    @JsonIgnore
    private final Set<CarroCliente> carroClienteSet = new HashSet<>();

    public CarroDTO() {
    }

    public CarroDTO(Long id_carro, String nome_carro, Montadora montadora) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarroDTO carroDTO = (CarroDTO) o;
        return id.equals(carroDTO.id) &&
                nome.equals(carroDTO.nome) &&
                Objects.equals(montadora, carroDTO.montadora) &&
                Objects.equals(carroClienteSet, carroDTO.carroClienteSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, montadora, carroClienteSet);
    }
}
