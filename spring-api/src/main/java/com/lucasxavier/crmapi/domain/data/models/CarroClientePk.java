package com.lucasxavier.crmapi.domain.data.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarroClientePk implements Serializable {

    private static final long serialVersionUID = 8809613658244290018L;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarroClientePk that = (CarroClientePk) o;
        return Objects.equals(carro, that.carro) &&
                Objects.equals(cliente, that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carro, cliente);
    }
}
