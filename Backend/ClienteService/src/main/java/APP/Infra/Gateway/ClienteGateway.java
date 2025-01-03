package APP.Infra.Gateway;

import APP.Domain.Response.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface ClienteGateway {

    public ResponseEntity<List<Cliente>> ListarClientes();
    public ResponseEntity<Cliente> BuscarClientesPorId(@RequestParam Long id);
    public ResponseEntity<Cliente> BuscarClientesPorDocumento(@RequestParam Long documento);
    public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome,
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
                                               @RequestParam String email,
                                               Double score);
    public ResponseEntity<Cliente> EditarCliente(@RequestParam Long id,
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
                                                 @RequestParam String email,
                                                 Double score);
    public ResponseEntity<Cliente> AlterarScoreClientes(@RequestParam Long id,
                                                        @RequestParam Double score);
    public void DeletarClientesPorId(@RequestParam Long id);

}
