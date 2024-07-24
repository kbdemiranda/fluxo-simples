package io.github.fluxo.application.service.impl;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.mapper.FaturaMapper;
import io.github.fluxo.application.service.FaturaService;
import io.github.fluxo.domain.model.Fatura;
import io.github.fluxo.infrastructure.repository.FaturaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository faturaRepository;
    private final FaturaMapper faturaMapper;

    public FaturaServiceImpl(FaturaRepository faturaRepository, FaturaMapper faturaMapper) {
        this.faturaRepository = faturaRepository;
        this.faturaMapper = faturaMapper;
    }

    @Override
    public FaturaDTO gerarFatura(FaturaDTO faturaDTO) {
        Fatura fatura = faturaMapper.toEntity(faturaDTO);
        Fatura faturaEntity = faturaRepository.save(fatura);
        return faturaMapper.toDTO(faturaEntity);
    }

    @Override
    public Page<FaturaDTO> listarFaturas(String descricao, String dataVencimento, String dataPagamento, Pageable pageable) {
        return faturaRepository.findByFilters(descricao, dataVencimento, dataPagamento, pageable)
                .map(faturaMapper::toDTO);
    }

    @Override
    public FaturaDTO buscarFaturaPorId(Long id) {
        return faturaMapper.toDTO(getFatura(id));
    }

    @Override
    public FaturaDTO atualizarFatura(Long id, FaturaDTO faturaDTO) {
        Fatura fatura = getFatura(id);
        fatura.setDescricao(faturaDTO.getDescricao());
        fatura.setDataVencimento(faturaDTO.getDataVencimento());
        fatura.setDataPagamento(faturaDTO.getDataPagamento());
        fatura.setValor(faturaDTO.getValor());
        fatura.setPaga(faturaDTO.getPaga());
        Fatura faturaEntity = faturaRepository.save(fatura);
        return faturaMapper.toDTO(faturaEntity);
    }

    @Override
    public void deletarFatura(Long id) {
        faturaRepository.delete(getFatura(id));
    }

    @Override
    public BigDecimal buscarTotalPagoPorPeriodo(String startDate, String endDate) {
        return faturaRepository.findTotalPagoPorPeriodo(startDate, endDate);
    }

    @Override
    public FaturaDTO pagarFatura(Long id) {
        Fatura fatura = getFatura(id);
        fatura.setDataPagamento(LocalDate.now());
        fatura.setPaga(true);
        Fatura faturaEntity = faturaRepository.save(fatura);
        return faturaMapper.toDTO(faturaEntity);
    }

    @Override
    public FaturaDTO cancelarPagamentoFatura(Long id) {
        Fatura fatura = getFatura(id);
        fatura.setDataPagamento(null);
        fatura.setPaga(false);
        Fatura faturaEntity = faturaRepository.save(fatura);
        return faturaMapper.toDTO(faturaEntity);
    }

    private Fatura getFatura(Long id){
        return faturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Fatura não encontrada"));
    }
}
