package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.CarroCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroClienteRepository extends JpaRepository<CarroCliente, Long> {

    @Query(value = "SELECT * FROM carro_cliente WHERE id_cliente = ?1", nativeQuery = true)
    Optional<List<CarroCliente>> findCarByClient(Long id);
}
