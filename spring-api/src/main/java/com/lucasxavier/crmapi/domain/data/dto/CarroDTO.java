package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id","nome", "montadoraId"})
public class CarroDTO extends RepresentationModel<CarroDTO> implements Serializable {

    private static final long serialVersionUID = -6488625267004062221L;

    private Long id;
    private String nome;
    @JsonProperty(value = "montadora_id")
    private Long montadoraId;

    public CarroDTO() {
    }

    public CarroDTO(Long id_carro, String nome_carro, Long montadoraId) {
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

}
