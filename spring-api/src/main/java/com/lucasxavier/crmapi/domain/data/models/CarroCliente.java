package com.lucasxavier.crmapi.domain.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "carro_cliente")
public class CarroCliente {

    @EmbeddedId
    private final CarroClientePk id = new CarroClientePk();
    private int ano;

    public CarroCliente(){}

    public CarroCliente(Carro carro, Cliente cliente, int ano) {
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

    @JsonIgnore
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
        CarroCliente that = (CarroCliente) o;
        return ano == that.ano &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ano);
    }
}
