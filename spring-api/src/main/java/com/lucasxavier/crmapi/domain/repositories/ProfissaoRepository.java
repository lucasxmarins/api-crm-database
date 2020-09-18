package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.Cliente;
import com.lucasxavier.crmapi.domain.data.models.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    @Query(value = "SELECT * FROM cliente WHERE codigo_pais = ?1 ", nativeQuery = true)
    List<Cliente> findAllClients(String cod);
}
