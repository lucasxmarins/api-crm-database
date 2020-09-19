package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query(value = "SELECT * FROM carro_montadora WHERE id_montadora = ?1", nativeQuery = true)
    Optional<List<Carro>> getCarsPerConstructor(Long id);
}
