package io.github.fluxo.application.service.impl;

import io.github.fluxo.application.service.FaturaService;
import io.github.fluxo.infrastructure.repository.FaturaRepository;
import org.springframework.stereotype.Service;

@Service
public class FaturaServiceImpl implements FaturaService {

    private final FaturaRepository faturaRepository;

    public FaturaServiceImpl(FaturaRepository faturaRepository) {
        this.faturaRepository = faturaRepository;
    }
}
