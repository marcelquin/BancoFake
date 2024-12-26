package App.Infra.UseCase.Account;

import App.Domain.Response.AcountResponse;
import App.Infra.Gateway.AcountGateway;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public class UseCaseAcountPost {

    private final AcountGateway acountGateway;


    public UseCaseAcountPost(AcountGateway acountGateway) {
        this.acountGateway = acountGateway;
    }

    public ResponseEntity<AcountResponse> NovaAcount(@RequestParam String nome,
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
                                                     @RequestParam Double score,
                                                     @RequestParam TIPOACOUNT tipoacount)
    {return acountGateway.NovaAcount(nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email, score, tipoacount);}
}
