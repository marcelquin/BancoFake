package App.Domain.Response;

import App.Infra.Persistence.Enum.STATUSTRANSACAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Transacao {

    private Long id;

    private String acuntePagador;
    private String acunteBeneficiario;
    private String codigo;
    private Double valor;

    @Enumerated(EnumType.STRING)
    private STATUSTRANSACAO statustransacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataTransacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAutorizacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public Transacao() {
    }

    public Transacao(Long id, String acuntePagador, String acunteBeneficiario, String codigo, Double valor, STATUSTRANSACAO statustransacao, LocalDateTime dataTransacao, LocalDateTime dataAutorizacao, LocalDateTime timeStamp) {
        this.id = id;
        this.acuntePagador = acuntePagador;
        this.acunteBeneficiario = acunteBeneficiario;
        this.codigo = codigo;
        this.valor = valor;
        this.statustransacao = statustransacao;
        this.dataTransacao = dataTransacao;
        this.dataAutorizacao = dataAutorizacao;
        this.timeStamp = timeStamp;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcuntePagador() {
        return acuntePagador;
    }

    public void setAcuntePagador(String acuntePagador) {
        this.acuntePagador = acuntePagador;
    }

    public String getAcunteBeneficiario() {
        return acunteBeneficiario;
    }

    public void setAcunteBeneficiario(String acunteBeneficiario) {
        this.acunteBeneficiario = acunteBeneficiario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public STATUSTRANSACAO getStatustransacao() {
        return statustransacao;
    }

    public void setStatustransacao(STATUSTRANSACAO statustransacao) {
        this.statustransacao = statustransacao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDateTime getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(LocalDateTime dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
