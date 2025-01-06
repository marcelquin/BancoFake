package App.Domain.Response;


import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;

public class AcountResponse {

    private Long id;

    private String cliente;

    private Long documento;

    private String acount;

    private String senhaAutenticacao;

    private String senhaAutorizacao;

    private String TIPOACOUNT;

    private Double saldo;

    private Double limite;
    private Double limiteDisponivel;

    private Boolean bloqueio;
    private List<String> Noticicacao;

    private Boolean Ativa;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AcountResponse() {
    }

    public AcountResponse(Long id, String cliente, Long documento, String acount, String senhaAutenticacao, String senhaAutorizacao, String TIPOACOUNT, Double saldo, Double limite, Double limiteDisponivel, Boolean bloqueio, List<String> noticicacao, Boolean ativa, LocalDateTime timeStamp) {
        this.id = id;
        this.cliente = cliente;
        this.documento = documento;
        this.acount = acount;
        this.senhaAutenticacao = senhaAutenticacao;
        this.senhaAutorizacao = senhaAutorizacao;
        this.TIPOACOUNT = TIPOACOUNT;
        this.saldo = saldo;
        this.limite = limite;
        this.limiteDisponivel = limiteDisponivel;
        this.bloqueio = bloqueio;
        Noticicacao = noticicacao;
        Ativa = ativa;
        this.timeStamp = timeStamp;
    }

    public Double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(Double limiteDisponivel) {
        if(limiteDisponivel < 0){throw new IllegalActionException();}
        this.limiteDisponivel = limiteDisponivel;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        if(limite < 0 ){throw new IllegalActionException();}
        this.limite = limite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getSenhaAutenticacao() {
        return senhaAutenticacao;
    }

    public void setSenhaAutenticacao(String senhaAutenticacao) {
        this.senhaAutenticacao = senhaAutenticacao;
    }

    public String getSenhaAutorizacao() {
        return senhaAutorizacao;
    }

    public void setSenhaAutorizacao(String senhaAutorizacao) {
        this.senhaAutorizacao = senhaAutorizacao;
    }

    public String getTIPOACOUNT() {
        return TIPOACOUNT;
    }

    public void setTIPOACOUNT(String TIPOACOUNT) {
        this.TIPOACOUNT = TIPOACOUNT;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        if(saldo < 0) {throw new IllegalActionException();}
        this.saldo = saldo;
    }

    public Boolean getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    public List<String> getNoticicacao() {
        return Noticicacao;
    }

    public void setNoticicacao(List<String> noticicacao) {
        Noticicacao = noticicacao;
    }

    public Boolean getAtiva() {
        return Ativa;
    }

    public void setAtiva(Boolean ativa) {
        Ativa = ativa;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }


}
