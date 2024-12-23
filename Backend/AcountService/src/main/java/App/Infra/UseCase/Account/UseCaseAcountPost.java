package App.Infra.UseCase.Account;

import App.Domain.Response.AcountResponse;
import App.Infra.Gateway.AcountGateway;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAcountPost {

    private final AcountGateway acountGateway;


    public UseCaseAcountPost(AcountGateway acountGateway) {
        this.acountGateway = acountGateway;
    }

    public ResponseEntity<AcountResponse> NovaAcount(@RequestParam String clienteNome,
                                                     @RequestParam String clienteSobrenome,
                                                     @RequestParam String cpfCnpj,
                                                     @RequestParam String telefone,
                                                     @RequestParam String email,
                                                     @RequestParam TIPOACOUNT tipoacount)
    {return acountGateway.NovaAcount(clienteNome, clienteSobrenome, cpfCnpj, telefone, email, tipoacount);}
}
