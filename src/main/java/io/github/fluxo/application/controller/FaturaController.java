package io.github.fluxo.application.controller;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.service.FaturaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @PostMapping
    public ResponseEntity<?> gerarFatura(@RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.gerarFatura(faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }
}
