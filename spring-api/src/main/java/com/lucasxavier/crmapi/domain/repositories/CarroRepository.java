package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.CarroVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroVO, Long> {
}
