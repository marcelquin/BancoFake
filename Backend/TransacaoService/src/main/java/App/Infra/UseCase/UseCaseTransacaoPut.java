package App.Infra.UseCase;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class UseCaseTransacaoPut {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPut(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public void FinalizarTransacao(@RequestBody AuthRequest authRequest)
    { transacaoGateway.FinalizarTransacao( authRequest);}
}
