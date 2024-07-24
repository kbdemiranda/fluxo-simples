package io.github.fluxo.application.service;

import io.github.fluxo.application.dto.FaturaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface FaturaService {

    FaturaDTO gerarFatura(FaturaDTO faturaDTO);
    Page<FaturaDTO> listarFaturas(String descricao, String dataVencimento, String dataPagamento,Pageable pageable);
}
