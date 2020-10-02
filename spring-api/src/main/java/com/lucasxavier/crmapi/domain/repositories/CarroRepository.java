package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query(value = "SELECT * FROM carro_montadora WHERE id_montadora = ?1", nativeQuery = true)
    Optional<List<Carro>> findCarsByManufacturer(Long id);

    @Query(value = "SELECT cm.* FROM carro_montadora cm " +
                    "JOIN carro_cliente cc " +
                    "ON cc.id_carro = cm.id_carro " +
                    "WHERE cc.id_cliente = ?1", nativeQuery = true)
    Optional<List<Carro>> findCarsByClient(Long id);
}
