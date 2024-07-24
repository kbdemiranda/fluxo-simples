package io.github.fluxo.application.controller;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.service.FaturaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping
    public ResponseEntity<Page<FaturaDTO>> listarFaturas(
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) String dataVencimento,
            @RequestParam(required = false) String dataPagamento,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int quantidade) {
        Page<FaturaDTO> faturaDTOs = faturaService.listarFaturas(descricao, dataVencimento, dataPagamento, PageRequest.of(pagina, quantidade));
        return ResponseEntity.ok(faturaDTOs);
    }

    @PostMapping
    public ResponseEntity<?> gerarFatura(@RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.gerarFatura(faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }
}
