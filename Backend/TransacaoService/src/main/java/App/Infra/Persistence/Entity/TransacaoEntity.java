package App.Infra.Persistence.Entity;

import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.STATUSTRANSACAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transacao")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acuntePagador;
    private String acunteBeneficiario;

    private Double valor;
    private String codigo;
    @Enumerated(EnumType.STRING)
    private STATUSTRANSACAO statustransacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataTransacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAutorizacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public TransacaoEntity() {
    }

    public TransacaoEntity(Long id, String acuntePagador, String acunteBeneficiario, Double valor, String codigo, STATUSTRANSACAO statustransacao, LocalDateTime dataTransacao, LocalDateTime dataAutorizacao, LocalDateTime timeStamp) {
        this.id = id;
        this.acuntePagador = acuntePagador;
        this.acunteBeneficiario = acunteBeneficiario;
        this.valor = valor;
        this.codigo = codigo;
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

    public Boolean verificaAcount(Boolean ativo, Boolean bloqueada, Double valor)
    {
        if(ativo == false){throw new IllegalActionException();}
        if(bloqueada == true){throw new IllegalActionException();}
        if(valor < 0){throw new IllegalActionException();}
        return true;
    }

}
