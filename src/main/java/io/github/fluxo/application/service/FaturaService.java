package io.github.fluxo.application.service;

import io.github.fluxo.application.dto.FaturaDTO;

public interface FaturaService {

    public FaturaDTO gerarFatura(FaturaDTO faturaDTO);
}
