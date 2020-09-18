package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.models.Carro;
import java.util.List;
import java.util.Objects;

public class MontadoraDTO {

    private Long id;
    private String nome;
    private List<Carro> carros;

    public MontadoraDTO() {
    }

    public MontadoraDTO(Long id_montadora, String nome_montadora) {
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
        MontadoraDTO that = (MontadoraDTO) o;
        return id.equals(that.id) &&
                nome.equals(that.nome) &&
                Objects.equals(carros, that.carros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, carros);
    }
}
