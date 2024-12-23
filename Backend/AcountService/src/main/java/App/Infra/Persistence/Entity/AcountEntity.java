package App.Infra.Persistence.Entity;

import App.Infra.Persistence.Enum.TIPOACOUNT;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Acount")
public class AcountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "clienteAcountEntity_Id", referencedColumnName = "id")
    private ClientAcountEntity clientAcount;

    @JoinColumn(unique = true)
    private String acount;

    private String senhaAutenticacao;

    private String senhaAutorizacao;

    @Enumerated(EnumType.STRING)
    private TIPOACOUNT TIPOACOUNT;

    private Double saldo;

    private Boolean bloqueio;
    private List<String> Noticicacao;

    private Boolean Ativa;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AcountEntity() {
    }

    public AcountEntity(Long id, ClientAcountEntity clientAcount, String acount, String senhaAutenticacao, String senhaAutorizacao, App.Infra.Persistence.Enum.TIPOACOUNT TIPOACOUNT, Double saldo, Boolean bloqueio, List<String> noticicacao, Boolean ativa, LocalDateTime timeStamp) {
        this.id = id;
        this.clientAcount = clientAcount;
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

    public ClientAcountEntity getClientAcount() {
        return clientAcount;
    }

    public void setClientAcount(ClientAcountEntity clientAcount) {
        this.clientAcount = clientAcount;
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

    public App.Infra.Persistence.Enum.TIPOACOUNT getTIPOACOUNT() {
        return TIPOACOUNT;
    }

    public void setTIPOACOUNT(App.Infra.Persistence.Enum.TIPOACOUNT TIPOACOUNT) {
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

    public List<String> getNoticicacao() {
        return Noticicacao;
    }

    public void setNoticicacao(List<String> noticicacao) {
        Noticicacao = noticicacao;
    }
}
