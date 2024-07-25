package io.github.fluxo.application.controller;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.service.FaturaService;
import io.github.fluxo.domain.model.Fatura;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<FaturaDTO> buscarFaturaPorId(@PathVariable Long id) {
        FaturaDTO faturaDTO = faturaService.buscarFaturaPorId(id);
        return ResponseEntity.ok(faturaDTO);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<?> gerarFatura(@RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.gerarFatura(faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<FaturaDTO> atualizarFatura(@PathVariable Long id, @RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.atualizarFatura(id, faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public ResponseEntity<?> deletarFatura(@PathVariable Long id) {
        faturaService.deletarFatura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-pago")
    public ResponseEntity<?> buscarTotalPagoPorPeriodo(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(faturaService.buscarTotalPagoPorPeriodo(startDate, endDate));
    }

    @PatchMapping("/{id}/pagar")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'CONTABIL')")
    public ResponseEntity<FaturaDTO> pagarFatura(@PathVariable Long id) {
        FaturaDTO dto = faturaService.pagarFatura(id);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @PatchMapping("/{id}/cancelar-pagamento")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'CONTABIL')")
    public ResponseEntity<FaturaDTO> cancelarPagamentoFatura(@PathVariable Long id) {
        FaturaDTO dto = faturaService.cancelarPagamentoFatura(id);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importFaturas(@RequestParam("file") MultipartFile file) {
        try {
            List<Fatura> faturas = csvToFaturas(file);
            faturaService.saveAll(faturas);
            return ResponseEntity.ok("Faturas importadas com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao importar faturas: " + e.getMessage());
        }
    }

    private List<Fatura> csvToFaturas(MultipartFile file) throws IOException {
        List<Fatura> faturas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                Fatura fatura = new Fatura();
                fatura.setDescricao(csvRecord.get("descricao"));
                fatura.setValor(new BigDecimal(csvRecord.get("valor")));
                fatura.setDataVencimento(LocalDate.parse(csvRecord.get("data_vencimento")));
                fatura.setDataPagamento(csvRecord.get("data_pagamento").isEmpty() ? null : LocalDate.parse(csvRecord.get("data_pagamento")));
                fatura.setPaga(Boolean.parseBoolean(csvRecord.get("paga")));
                faturas.add(fatura);
            }
        }

        return faturas;
    }
}
