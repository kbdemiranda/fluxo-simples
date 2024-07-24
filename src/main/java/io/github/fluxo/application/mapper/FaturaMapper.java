package io.github.fluxo.application.mapper;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.domain.model.Fatura;
import org.springframework.stereotype.Component;

@Component
public class FaturaMapper {

    public FaturaDTO toDTO(Fatura fatura) {
        FaturaDTO dto = new FaturaDTO();
        dto.setId(fatura.getId());
        dto.setDescricao(fatura.getDescricao());
        dto.setValor(fatura.getValor());
        dto.setDataVencimento(fatura.getDataVencimento());
        dto.setDataPagamento(fatura.getDataPagamento());
        dto.setPaga(fatura.getPaga());
        return dto;
    }

    public Fatura toEntity(FaturaDTO faturaDTO) {
        return Fatura.builder()
                .id(faturaDTO.getId())
                .descricao(faturaDTO.getDescricao())
                .valor(faturaDTO.getValor())
                .dataVencimento(faturaDTO.getDataVencimento())
                .dataPagamento(faturaDTO.getDataPagamento())
                .paga(faturaDTO.getPaga())
                .build();
    }
}

