package io.github.fluxo.application.service.impl;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.mapper.FaturaMapper;
import io.github.fluxo.application.service.FaturaService;
import io.github.fluxo.domain.model.Fatura;
import io.github.fluxo.infrastructure.repository.FaturaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
