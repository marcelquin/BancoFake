package App.Infra.Persistence.Entity;

import App.Infra.Persistence.Enum.TIPOTRANSACAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auth")
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuth;
    private Long idTransacao;
    @Enumerated(EnumType.STRING)
    private TIPOTRANSACAO tipotransacao;
    private Boolean bloqueio;
    private Boolean Ativo;
    private Boolean auth;
    private LocalDateTime dataAuth;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AuthEntity() {
    }

    public AuthEntity(Long idAuth, Long idTransacao, TIPOTRANSACAO tipotransacao, Boolean bloqueio, Boolean ativo, Boolean auth, LocalDateTime dataAuth, LocalDateTime timeStamp) {
        this.idAuth = idAuth;
        this.idTransacao = idTransacao;
        this.tipotransacao = tipotransacao;
        this.bloqueio = bloqueio;
        Ativo = ativo;
        this.auth = auth;
        this.dataAuth = dataAuth;
        this.timeStamp = timeStamp;
    }

    public Long getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(Long idAuth) {
        this.idAuth = idAuth;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public TIPOTRANSACAO getTipotransacao() {
        return tipotransacao;
    }

    public void setTipotransacao(TIPOTRANSACAO tipotransacao) {
        this.tipotransacao = tipotransacao;
    }

    public Boolean getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    public Boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(Boolean ativo) {
        Ativo = ativo;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public LocalDateTime getDataAuth() {
        return dataAuth;
    }

    public void setDataAuth(LocalDateTime dataAuth) {
        this.dataAuth = dataAuth;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
