package App.Domain.Response;


import App.Infra.Exceptions.IllegalActionException;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Cliente {

    private Long id;

    private String nome;

    private String sobrenome;

    private Long documento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private Endereco enderecoEntity;

    private Contato contatoEntity;

    private String notificacao;

    private Double score;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String sobrenome, Long documento, LocalDate dataNascimento, Endereco endereco, Contato contato, String notificacao, Double score, LocalDateTime timeStamp) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.enderecoEntity = endereco;
        this.contatoEntity = contato;
        this.notificacao = notificacao;
        this.score = score;
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

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return enderecoEntity;
    }

    public void setEndereco(Endereco endereco) {
        this.enderecoEntity = endereco;
    }

    public Contato getContato() {
        return contatoEntity;
    }

    public void setContato(Contato contato) {
        this.contatoEntity = contato;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    public Double getScore() {
        return score;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
