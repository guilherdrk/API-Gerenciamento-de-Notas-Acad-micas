package com.example.gerenciadornotas.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class NotaDTO {

    private Long id; // Opcional, usado para retorno ou update

    @NotBlank(message = "O título da cadeira é obrigatorio")
    @Size(min = 3, max = 100, message = "O titulo da cadeira deve ter entre 3 a 100 caracteres")
    private String tituloCadeira;

    @NotNull(message = "O valor da nota é obrigatório")
    @DecimalMin(value = "0.0", message="A nota mínima é 0.0")
    @DecimalMax(value = "10.0", message = "A nota máxima é 10.0")
    private Double valorNota;

    @NotNull(message = "A data de lançamento é obrigatória")
    private LocalDate dataLancamento;

    private String observacoes;

    public NotaDTO() {}

    public NotaDTO(Long id, String tituloCadeira, Double valorNota, LocalDate dataLancamento, String observacoes) {
        this.id = id;
        this.tituloCadeira = tituloCadeira;
        this.valorNota = valorNota;
        this.dataLancamento = dataLancamento;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloCadeira() {
        return tituloCadeira;
    }

    public void setTituloCadeira(String tituloCadeira) {
        this.tituloCadeira = tituloCadeira;
    }

    public Double getValorNota() {
        return valorNota;
    }

    public void setValorNota(Double valorNota) {
        this.valorNota = valorNota;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
