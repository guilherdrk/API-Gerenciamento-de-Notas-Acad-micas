package com.example.gerenciadornotas.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Campo obrigatorio
    private String tituloCadeira;

    @Column(nullable = false)
    private Double valorNota;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    private String observacoes;


    public Nota(){}

    public Nota(Long id, String tituloCadeira, Double valorNota, LocalDate dataLancamento, String observacoes) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", tituloCadeira='" + tituloCadeira + '\'' +
                ", valorNota=" + valorNota +
                ", dataLancamento=" + dataLancamento +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
