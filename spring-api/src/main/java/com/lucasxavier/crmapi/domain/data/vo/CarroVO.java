package com.lucasxavier.crmapi.domain.data.vo;

import com.lucasxavier.crmapi.domain.data.models.Cliente;
import com.lucasxavier.crmapi.domain.data.models.Montadora;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CarroVO extends RepresentationModel<CarroVO> implements Serializable {

    private static final long serialVersionUID = -6488625267004062221L;
    private Long id;
    private String nome;
    private Montadora montadora;

    private final Set<Cliente> clientes = new HashSet<>();

    public CarroVO() {
    }

    public CarroVO(Long id_carro, String nome_carro, Montadora montadora) {
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

    public Set<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarroVO carroVO = (CarroVO) o;
        return Objects.equals(id, carroVO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
