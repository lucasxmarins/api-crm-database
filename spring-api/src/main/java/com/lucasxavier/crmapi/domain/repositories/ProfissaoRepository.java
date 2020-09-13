package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.models.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {
}
