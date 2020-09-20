package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasxavier.crmapi.domain.data.models.Carro;
import com.lucasxavier.crmapi.domain.data.models.CarroClientePk;
import com.lucasxavier.crmapi.domain.data.models.Cliente;
import java.util.Objects;

public class CarroClienteDTO {

    private final CarroClientePk id = new CarroClientePk();
    private int ano;

    public CarroClienteDTO(){}

    public CarroClienteDTO(Carro carro, Cliente cliente, int ano) {
        id.setCarro(carro);
        id.setCliente(cliente);
        this.ano = ano;
    }

    public Carro getCarro(){
        return id.getCarro();
    }

    public void setCarro(Carro carro){
        id.setCarro(carro);
    }

    public Cliente getCliente(){
        return id.getCliente();
    }

    public void setCliente(Cliente cliente){
        id.setCliente(cliente);
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
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ano);
    }
}
