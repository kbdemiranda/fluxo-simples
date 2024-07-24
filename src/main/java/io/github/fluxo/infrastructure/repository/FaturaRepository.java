package io.github.fluxo.infrastructure.repository;

import io.github.fluxo.domain.model.Fatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query("SELECT f FROM Fatura f " +
            "WHERE (:descricao IS NULL OR UPPER(f.descricao) LIKE UPPER(CONCAT('%', :descricao, '%'))) " +
            "AND (:dataVencimento IS NULL OR TO_CHAR(f.dataVencimento, 'YYYY-MM-DD') = :dataVencimento) " +
            "AND (:dataPagamento IS NULL OR TO_CHAR(f.dataPagamento, 'YYYY-MM-DD') = :dataPagamento) ")
    Page<Fatura> findByFilters(@Param("descricao") String descricao,
                               @Param("dataVencimento") String dataVencimento,
                               @Param("dataPagamento") String dataPagamento,
                               Pageable pageable);

    @Query("SELECT SUM(f.valor) FROM Fatura f " +
            "WHERE TO_CHAR(f.dataPagamento, 'YYYY-MM-DD') IS NOT NULL " +
            "AND TO_CHAR(f.dataPagamento, 'YYYY-MM-DD') BETWEEN :startDate AND :endDate " +
            "AND f.paga = true")
    BigDecimal findTotalPagoPorPeriodo(@Param("startDate") String startDate,
                                       @Param("endDate") String endDate);

}
