package App.Infra.UseCase;

import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseTransacaoPost {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPost(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public ResponseEntity<Transacao> novoSaque(@RequestParam String acount, @RequestParam String senhaAutorizacao, @RequestParam Double valor)
    { return transacaoGateway.novoSaque(acount,senhaAutorizacao, valor);}

    public ResponseEntity<Transacao> novoDeposito(@RequestParam String acountPagador,@RequestParam String senhaAutorizacao,@RequestParam String acountBeneficiario, @RequestParam Double valor)
    { return transacaoGateway.novoDeposito(acountPagador,senhaAutorizacao, acountBeneficiario, valor);}
}
