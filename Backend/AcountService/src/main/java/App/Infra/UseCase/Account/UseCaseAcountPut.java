package App.Infra.UseCase.Account;

import App.Domain.Response.AcountResponse;
import App.Infra.Gateway.AcountGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAcountPut {

    private final AcountGateway acountGateway;


    public UseCaseAcountPut(AcountGateway acountGateway) {
        this.acountGateway = acountGateway;
    }

    public ResponseEntity<AcountResponse> AlterarLimite(@RequestParam Long id, @RequestParam Double novoLimite)
    {return acountGateway.AlterarLimite(id, novoLimite);}


    public ResponseEntity<AcountResponse> BloquearAcount(@RequestParam Long id, @RequestParam String justificativa)
    {return acountGateway.BloquearAcount(id, justificativa);}
}
