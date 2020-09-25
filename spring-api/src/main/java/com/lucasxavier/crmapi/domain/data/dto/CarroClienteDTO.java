package com.lucasxavier.crmapi.domain.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class CarroClienteDTO extends RepresentationModel<CarroClienteDTO> {

    private Long clienteId;
    private Long carroId;
    private int ano;

    public CarroClienteDTO(){}

    public CarroClienteDTO(Long clienteId, Long carroId, int ano) {
        this.clienteId = clienteId;
        this.carroId = carroId;
        this.ano = ano;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarroClienteDTO that = (CarroClienteDTO) o;
        return ano == that.ano &&
                Objects.equals(clienteId, that.clienteId) &&
                Objects.equals(carroId, that.carroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, carroId, ano);
    }
}
