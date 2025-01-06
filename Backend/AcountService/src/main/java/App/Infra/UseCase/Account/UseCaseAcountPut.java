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

    public ResponseEntity<AcountResponse> BloquearAcount(@RequestParam Long id, @RequestParam String justificativa)
    {return acountGateway.BloquearAcount(id, justificativa);}

    public ResponseEntity<AcountResponse> alterarLimite(@RequestParam Long id, @RequestParam Double novoLimite)
    {return acountGateway.alterarLimite(id, novoLimite);}
}
