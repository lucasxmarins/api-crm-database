package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonPropertyOrder({"id","nome", "montadoraId"})
public class CarroDTOv2 extends RepresentationModel<CarroDTOv2> implements Serializable {

    private static final long serialVersionUID = -6488625267004062221L;

    private Long id;
    private String nome;

    @JsonProperty(value = "montadora_id")
    private Long montadoraId;

    @JsonIgnore
    private final Set<CarroCliente> carroClienteSet = new HashSet<>();

    public CarroDTOv2() {
    }

    public CarroDTOv2(Long id_carro, String nome_carro, Long montadoraId) {
        this.id = id_carro;
        this.nome = nome_carro;
        this.montadoraId = montadoraId;
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

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraId(Long montadoraId) {
        this.montadoraId = montadoraId;
    }

    @JsonIgnore
    public Set<CarroCliente> getCarroClienteSet() {
        return carroClienteSet;
    }


}
