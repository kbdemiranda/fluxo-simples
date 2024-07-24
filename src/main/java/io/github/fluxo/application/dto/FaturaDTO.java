package io.github.fluxo.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FaturaDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("descricao")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @JsonProperty("valor")
    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;
    @JsonProperty("data_vencimento")
    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;
    @JsonProperty("data_pagamento")
    private LocalDate dataPagamento;
    @JsonProperty("paga")
    private Boolean paga = false;
}
