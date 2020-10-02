package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.entities.CarroCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarroClienteRepository extends JpaRepository<CarroCliente, Long> {

    @Query(value = "SELECT * FROM carro_cliente WHERE id_cliente = ?1", nativeQuery = true)
    Optional<List<CarroCliente>> findCarByClient(Long id);

    @Query(value = "SELECT cc.* from cliente c " +
                    "JOIN carro_cliente cc " +
                    "ON c.id_cliente = cc.id_cliente " +
                    "WHERE cc.id_carro = ?1", nativeQuery = true)
    Optional<List<CarroCliente>> findAllClientCars(Long carId);

    @Query(value = "SELECT * from carro_cliente " +
                   "WHERE id_cliente = ?1 AND id_carro = ?2",nativeQuery = true)
    Optional<List<CarroCliente>> finsClientCarById(Long clientId, Long carroId);
}
