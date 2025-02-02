package App.Util;

import App.Domain.Response.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "Cliente-service", url = "http://localhost:8081/cliente")
public interface ClienteService {


    @GetMapping("/BuscarClientesPorDocumento")
    public ResponseEntity<Cliente> BuscarClientesPorDocumento(@RequestParam Long documento);

    @PostMapping("/NovoCliente")
    public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome,
                                               @RequestParam String sobrenome,
                                               @RequestParam Long documento,
                                               @RequestParam LocalDate dataNascimento,
                                               @RequestParam String logradouro,
                                               @RequestParam String numero,
                                               @RequestParam String bairro,
                                               @RequestParam String referencia,
                                               @RequestParam String cep,
                                               @RequestParam Long prefixo,
                                               @RequestParam Long telefone,
                                               @RequestParam String email,
                                               @RequestParam Double score);

}
