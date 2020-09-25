package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE codigo_pais = ?1 ", nativeQuery = true)
    Optional<List<Cliente>> findAllClientesByPaisCod(String cod);

    @Query(value = "SELECT * FROM cliente WHERE id_profissao = ?1 ", nativeQuery = true)
    Optional<List<Cliente>> findAllClientesByProfissaoId(long id);

    @Query(value = "SELECT * FROM cliente " +
                    "JOIN carro_cliente " +
                    "ON carro_cliente.id_cliente = cliente.id_cliente " +
                    "JOIN carro_montadora " +
                    "ON carro_cliente.id_carro = carro_montadora.id_carro " +
                    "WHERE id_montadora = ?1", nativeQuery = true)
    Optional<List<Cliente>> findAllClientesByMontadoraId(long id);

}
