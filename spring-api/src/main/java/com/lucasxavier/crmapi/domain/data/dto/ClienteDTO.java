package com.lucasxavier.crmapi.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import com.lucasxavier.crmapi.domain.data.models.Pais;
import com.lucasxavier.crmapi.domain.data.models.Profissao;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClienteDTO {

    private Long id;
    private String primeiro_nome;
    private String ultimo_nome;
    private String email;
    private String sexo;
    private String cidade;
    private String empresa;
    private String etnia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Calendar nascimento;

    private Pais pais;
    private Profissao profissao;
    private Set<CarroCliente> carros = new HashSet<>();

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String primeiro_nome, String ultimo_nome, String email, String sexo,
                      String cidade, String empresa, String etnia, Calendar nascimento, Pais pais,
                      Profissao profissao) {
        this.id = id;
        this.primeiro_nome = primeiro_nome;
        this.ultimo_nome = ultimo_nome;
        this.email = email;
        this.sexo = sexo;
        this.cidade = cidade;
        this.empresa = empresa;
        this.etnia = etnia;
        this.nascimento = nascimento;
        this.pais = pais;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getUltimo_nome() {
        return ultimo_nome;
    }

    public void setUltimo_nome(String ultimo_nome) {
        this.ultimo_nome = ultimo_nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Set<CarroCliente> getCarros() {
        return carros;
    }

    public void setCarros(Set<CarroCliente> carros) {
        this.carros = carros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return id.equals(that.id) &&
                primeiro_nome.equals(that.primeiro_nome) &&
                Objects.equals(ultimo_nome, that.ultimo_nome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(sexo, that.sexo) &&
                Objects.equals(cidade, that.cidade) &&
                Objects.equals(empresa, that.empresa) &&
                Objects.equals(etnia, that.etnia) &&
                Objects.equals(nascimento, that.nascimento) &&
                Objects.equals(pais, that.pais) &&
                Objects.equals(profissao, that.profissao) &&
                Objects.equals(carros, that.carros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, primeiro_nome, ultimo_nome, email, sexo, cidade, empresa, etnia, nascimento, pais, profissao, carros);
    }
}
