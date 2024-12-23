package App.Infra.UseCase.Account;

import App.Domain.Response.AcountResponse;
import App.Infra.Gateway.AcountGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseAcounteGet {

    private final AcountGateway acountGateway;


    public UseCaseAcounteGet(AcountGateway acountGateway) {
        this.acountGateway = acountGateway;
    }

    public ResponseEntity<List<AcountResponse>> ListarAcounts()
    {return acountGateway.ListarAcounts();}

    public ResponseEntity<AcountResponse> BuscarAcountPorId(@RequestParam Long id)
    {return acountGateway.BuscarAcountPorId(id);}

    public ResponseEntity<AcountResponse>BuscarAcountPorAcountNumber(@RequestParam String acountNumber)
    {return acountGateway.BuscarAcountPorAcountNumber(acountNumber);}
}
