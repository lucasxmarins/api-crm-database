package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE codigo_pais = ?1 ", nativeQuery = true)
    List<Cliente> findClientsPerCountry(String cod);

    @Query(value = "SELECT * FROM cliente WHERE id_profissao = ?1 ", nativeQuery = true)
    List<Cliente> findClientsPerJob(long id);
}
