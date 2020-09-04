package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
