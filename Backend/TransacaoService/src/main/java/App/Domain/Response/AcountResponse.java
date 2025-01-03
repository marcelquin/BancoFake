package App.Domain.Response;


import App.Infra.Exceptions.IllegalActionException;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Boolean bloqueio;
    private List<String> Noticicacao;

    private Boolean Ativa;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AcountResponse() {
    }

    public AcountResponse(Long id, String cliente, Long documento, String acount, String senhaAutenticacao, String senhaAutorizacao, String TIPOACOUNT, Double saldo, Boolean bloqueio, List<String> noticicacao, Boolean ativa, LocalDateTime timeStamp) {
        this.id = id;
        this.cliente = cliente;
        this.documento = documento;
        this.acount = acount;
        this.senhaAutenticacao = senhaAutenticacao;
        this.senhaAutorizacao = senhaAutorizacao;
        this.TIPOACOUNT = TIPOACOUNT;
        this.saldo = saldo;
        this.bloqueio = bloqueio;
        Noticicacao = noticicacao;
        Ativa = ativa;
        this.timeStamp = timeStamp;
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

    public Boolean checkAcount()
    {
        if(this.bloqueio == true){throw new IllegalActionException();}
        if(this.Ativa == false){throw new IllegalActionException();}
        return Boolean.TRUE;
    }

    public Double saque(Double valor)
    {
        if(this.saldo < valor){throw new IllegalActionException();}
        Boolean check = checkAcount();
        if(check == true)
        {
            this.saldo = this.saldo - valor;
        }
        return saldo;
    }

    public Double depositto(Double valor)
    {
        Boolean check = checkAcount();
        if(check == true)
        {
            this.saldo = this.saldo + valor;
        }
        return saldo;
    }

}
