package APP.Infra.Persistence.Entity;

import APP.Infra.Exceptions.IllegalActionException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "Cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    @JoinColumn(unique = true)
    private Long documento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EnderecoEntity_id", referencedColumnName = "id")
    private EnderecoEntity enderecoEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contatoEntity_id", referencedColumnName = "id")
    private ContatoEntity contatoEntity;

    private String notificacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String nome, String sobrenome, Long documento, LocalDate dataNascimento, EnderecoEntity endereco, ContatoEntity contato, String notificacao, LocalDateTime timeStamp) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.enderecoEntity = endereco;
        this.contatoEntity = contato;
        this.notificacao = notificacao;
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

    public EnderecoEntity getEndereco() {
        return enderecoEntity;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.enderecoEntity = endereco;
    }

    public ContatoEntity getContato() {
        return contatoEntity;
    }

    public void setContato(ContatoEntity contato) {
        this.contatoEntity = contato;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
