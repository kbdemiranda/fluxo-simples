package io.github.fluxo.application.controller;

import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.service.FaturaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<?> gerarFatura(@RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.gerarFatura(faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturaDTO> atualizarFatura(@PathVariable Long id, @RequestBody @Valid FaturaDTO faturaDTO) {
        FaturaDTO dto = faturaService.atualizarFatura(id, faturaDTO);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFatura(@PathVariable Long id) {
        faturaService.deletarFatura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-pago")
    public ResponseEntity<?> buscarTotalPagoPorPeriodo(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(faturaService.buscarTotalPagoPorPeriodo(startDate, endDate));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<FaturaDTO> pagarFatura(@PathVariable Long id) {
        FaturaDTO dto = faturaService.pagarFatura(id);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }

    @PatchMapping("/{id}/cancelar-pagamento")
    public ResponseEntity<FaturaDTO> cancelarPagamentoFatura(@PathVariable Long id) {
        FaturaDTO dto = faturaService.cancelarPagamentoFatura(id);
        return ResponseEntity.created(URI.create("/fatura/" + dto.getId())).body(dto);
    }
}
