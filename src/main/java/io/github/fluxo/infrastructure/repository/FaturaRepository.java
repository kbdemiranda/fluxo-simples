package io.github.fluxo.infrastructure.repository;

import io.github.fluxo.domain.model.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, UUID> {
}
