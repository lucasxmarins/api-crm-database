package com.lucasxavier.crmapi.domain.repositories;

import com.lucasxavier.crmapi.domain.data.entities.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

    @Query(value = "SELECT * FROM profissao WHERE nome_profissao = ?1 ", nativeQuery = true)
    Optional<List<Profissao>> findProfissaoByNome(String nome);

}
