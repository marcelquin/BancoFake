package APP.Infra.UseCase;

import APP.Domain.Response.Cliente;
import APP.Infra.Gateway.ClienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class UseCaseClientePut {

    private final ClienteGateway clienteGateway;

    public UseCaseClientePut(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }


    public ResponseEntity<Cliente> EditarCliente(@RequestParam Long id,
                                                 @RequestParam String userMaster,
                                                 @RequestParam String passwordMaster,
                                                 @RequestParam String nome,
                                                 @RequestParam String sobrenome,
                                                 @RequestParam Long documento,
                                                 @RequestParam LocalDate dataNascimento,
                                                 String logradouro,
                                                 @RequestParam String numero,
                                                 String bairro,
                                                 String referencia,
                                                 @RequestParam String cep,
                                                 @RequestParam Long prefixo,
                                                 @RequestParam Long telefone,
                                                 @RequestParam String email)
    {return clienteGateway.EditarCliente(id,userMaster,passwordMaster, nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email);}


}
