package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, String> {

    @Query(value = "SELECT * FROM pais p WHERE p.codigo_pais = ?1", nativeQuery = true)
    Optional<List<Pais>> findByCod(String cod);
}
