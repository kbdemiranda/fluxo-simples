package io.github.fluxo.application.service;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.domain.model.Fatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface FaturaService {

    FaturaDTO gerarFatura(FaturaDTO faturaDTO);
    Page<FaturaDTO> listarFaturas(String descricao, String dataVencimento, String dataPagamento,Pageable pageable);
    FaturaDTO buscarFaturaPorId(Long id);
    FaturaDTO atualizarFatura(Long id, FaturaDTO faturaDTO);
    void deletarFatura(Long id);
    BigDecimal buscarTotalPagoPorPeriodo(String startDate, String endDate);

    FaturaDTO pagarFatura(Long id);

    FaturaDTO cancelarPagamentoFatura(Long id);

    void saveAll(List<Fatura> faturas);
}
