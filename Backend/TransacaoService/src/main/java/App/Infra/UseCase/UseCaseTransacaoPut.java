package App.Infra.UseCase;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseTransacaoPut {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPut(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public void FinalizarTransacaoSaque(@RequestBody AuthRequest authRequest)
    { transacaoGateway.FinalizarTransacaoSaque( authRequest);}

    public void FinalizarTransacaoDeposisito(@RequestBody AuthRequest authRequest)
    { transacaoGateway.FinalizarTransacaoDeposisito(authRequest);}

    public void FinalizarTransacaoAdicionarSaldo(@RequestBody AuthRequest authRequest)
    { transacaoGateway.FinalizarTransacaoAdicionarSaldo(authRequest);}

    public ResponseEntity<Transacao> AdicionarSaldo(@RequestParam String acountPagador, @RequestParam String senhaAutenticacao, @RequestParam Double valor)
    { return transacaoGateway.AdicionarSaldo(acountPagador, senhaAutenticacao, valor);}
}
