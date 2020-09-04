package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
