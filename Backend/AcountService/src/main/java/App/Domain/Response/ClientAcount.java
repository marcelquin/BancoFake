package App.Domain.Response;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;



public class ClientAcount {

    private Long id;

    private String nome;
    private String sobrenome;

    private String cpjCnpj;
    private String telefone;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public ClientAcount() {
    }

    public ClientAcount(Long id, String nome, String sobrenome, String cpjCnpj, String telefone, String email, LocalDateTime timeStamp) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpjCnpj = cpjCnpj;
        this.telefone = telefone;
        this.email = email;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpjCnpj() {
        return cpjCnpj;
    }

    public void setCpjCnpj(String cpjCnpj) {
        this.cpjCnpj = cpjCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
