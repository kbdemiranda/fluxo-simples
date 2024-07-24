package io.github.fluxo.application.service.impl;

import io.github.fluxo.domain.model.Fatura;
import io.github.fluxo.infrastructure.repository.FaturaRepository;
import io.github.fluxo.application.dto.FaturaDTO;
import io.github.fluxo.application.mapper.FaturaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FaturaServiceImplTest {

    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private FaturaMapper faturaMapper = new FaturaMapper();

    @InjectMocks
    private FaturaServiceImpl faturaService;

    @BeforeEach
    public void init() {
        faturaService = new FaturaServiceImpl(faturaRepository, faturaMapper);
    }

    @Test
    void gerarFatura(){
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        when(faturaMapper.toEntity(faturaDTO)).thenReturn(fatura);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);
        when(faturaRepository.save(fatura)).thenReturn(fatura);

        FaturaDTO faturaDTOResult = faturaService.gerarFatura(faturaDTO);

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getValor());
    }


    @Test
    void listarFaturasComTodosParametros() {
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        List<Fatura> faturas = List.of(fatura);
        Page<Fatura> faturaPage = new PageImpl<>(faturas);

        when(faturaRepository.findByFilters(any(), any(), any(), any())).thenReturn(faturaPage);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        Page<FaturaDTO> faturaDTOResult = faturaService.listarFaturas("Teste", "2024-08-01", "2024-07-25", PageRequest.of(0, 10));

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getContent().get(0).getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getContent().get(0).getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getContent().get(0).getValor());
    }

    @Test
    void listarFaturasComDescricao() {
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        List<Fatura> faturas = List.of(fatura);
        Page<Fatura> faturaPage = new PageImpl<>(faturas);

        when(faturaRepository.findByFilters(any(), any(), any(), any())).thenReturn(faturaPage);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        Page<FaturaDTO> faturaDTOResult = faturaService.listarFaturas("Teste", "", "", PageRequest.of(0, 10));

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getContent().get(0).getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getContent().get(0).getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getContent().get(0).getValor());
    }

    @Test
    void listarFaturasComDataVencimento() {
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        List<Fatura> faturas = List.of(fatura);
        Page<Fatura> faturaPage = new PageImpl<>(faturas);

        when(faturaRepository.findByFilters(any(), any(), any(), any())).thenReturn(faturaPage);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        Page<FaturaDTO> faturaDTOResult = faturaService.listarFaturas("", "2024-08-01", "", PageRequest.of(0, 10));

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getContent().get(0).getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getContent().get(0).getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getContent().get(0).getValor());
    }

    @Test
    void listarFaturasComDataPagamento() {
        FaturaDTO faturaDTO = getFaturaDTO();
        faturaDTO.setDataPagamento(LocalDate.of(2024, 7, 25));

        Fatura fatura = getFatura();
        fatura.setDataPagamento(LocalDate.of(2024, 7, 25));

        List<Fatura> faturas = List.of(fatura);
        Page<Fatura> faturaPage = new PageImpl<>(faturas);

        when(faturaRepository.findByFilters(any(), any(), any(), any())).thenReturn(faturaPage);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        Page<FaturaDTO> faturaDTOResult = faturaService.listarFaturas("", "", "2024-07-25", PageRequest.of(0, 10));

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getContent().get(0).getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getContent().get(0).getDataVencimento());
        assertEquals(faturaDTO.getDataPagamento(), faturaDTOResult.getContent().get(0).getDataPagamento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getContent().get(0).getValor());
    }


    @Test
    void listarFaturas(){
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        List<Fatura> faturas = List.of(fatura);
        Page<Fatura> faturaPage = new PageImpl<>(faturas);

        when(faturaRepository.findByFilters(any(), any(), any(), any())).thenReturn(faturaPage);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        Page<FaturaDTO> faturaDTOResult = faturaService.listarFaturas("", "", "", PageRequest.of(0, 10));

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getContent().get(0).getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getContent().get(0).getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getContent().get(0).getValor());
    }

    @Test
    void buscarFaturaPorId(){
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();

        when(faturaRepository.findById(any())).thenReturn(Optional.of(fatura));
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        FaturaDTO faturaDTOResult = faturaService.buscarFaturaPorId(1L);

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getValor());
    }

    @Test
    void atualizarFatura(){
        Long id = 1L;
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();
        fatura.setId(id);

        when(faturaRepository.findById(id)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(fatura)).thenReturn(fatura);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        FaturaDTO faturaDTOResult = faturaService.atualizarFatura(id, faturaDTO);

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getValor());
    }

    @Test
    void deletarFatura(){
        Long id = 1L;
        Fatura fatura = getFatura();
        fatura.setId(id);

        when(faturaRepository.findById(id)).thenReturn(Optional.of(fatura));

        faturaService.deletarFatura(id);

        verify(faturaRepository, times(1)).delete(fatura);
    }

    @Test
    void buscarTotalPagoPorPeriodo(){
        BigDecimal totalPago = BigDecimal.TEN;

        when(faturaRepository.findTotalPagoPorPeriodo(any(), any())).thenReturn(totalPago);

        BigDecimal totalPagoResult = faturaService.buscarTotalPagoPorPeriodo("2021-01-01", "2021-12-31");

        assertEquals(totalPago, totalPagoResult);
    }

    @Test
    void pagarFatura(){
        Long id = 1L;
        FaturaDTO faturaDTO = getFaturaDTO();
        faturaDTO.setDataPagamento(LocalDate.now());
        faturaDTO.setPaga(true);

        Fatura fatura = getFatura();
        fatura.setId(id);
        fatura.setDataPagamento(null);
        fatura.setPaga(false);

        when(faturaRepository.findById(id)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(fatura)).thenReturn(fatura);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        FaturaDTO faturaDTOResult = faturaService.pagarFatura(id);

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getValor());
        assertEquals(faturaDTO.getDataPagamento(), faturaDTOResult.getDataPagamento());
        assertTrue(faturaDTOResult.getPaga());
    }

    @Test
    void cancelarPagamentoFatura(){
        Long id = 1L;
        FaturaDTO faturaDTO = getFaturaDTO();

        Fatura fatura = getFatura();
        fatura.setId(id);
        fatura.setDataPagamento(LocalDate.now());
        fatura.setPaga(true);

        when(faturaRepository.findById(id)).thenReturn(Optional.of(fatura));
        when(faturaRepository.save(fatura)).thenReturn(fatura);
        when(faturaMapper.toDTO(fatura)).thenReturn(faturaDTO);

        FaturaDTO faturaDTOResult = faturaService.cancelarPagamentoFatura(id);

        assertEquals(faturaDTO.getDescricao(), faturaDTOResult.getDescricao());
        assertEquals(faturaDTO.getDataVencimento(), faturaDTOResult.getDataVencimento());
        assertEquals(faturaDTO.getValor(), faturaDTOResult.getValor());
        assertNull(faturaDTOResult.getDataPagamento());
        assertFalse(faturaDTOResult.getPaga());
    }

    @Test
    void saveAll(){
        Fatura fatura = getFatura();
        List<Fatura> faturas = List.of(fatura);

        faturaService.saveAll(faturas);

        verify(faturaRepository, times(1)).saveAll(faturas);
    }

    private static FaturaDTO getFaturaDTO() {
        FaturaDTO faturaDTO = new FaturaDTO();
        faturaDTO.setDescricao("Fatura de teste");
        faturaDTO.setDataVencimento(LocalDate.of(2024, 8, 1));
        faturaDTO.setValor(BigDecimal.TEN);
        return faturaDTO;
    }

    private static Fatura getFatura() {
        Fatura fatura = new Fatura();
        fatura.setDescricao("Fatura de teste");
        fatura.setDataVencimento(LocalDate.of(2024, 8, 1));
        fatura.setValor(BigDecimal.TEN);
        return fatura;
    }

}
