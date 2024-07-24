package io.github.fluxo.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class FaturaDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("descricao")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @JsonProperty("valor")
    @NotBlank(message = "Valor é obrigatório")
    private BigDecimal valor;
    @JsonProperty("dataVencimento")
    @NotBlank(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;
    @JsonProperty("dataPagamento")
    private LocalDate dataPagamento;
    @JsonProperty("paga")
    private Boolean paga = false;
}
