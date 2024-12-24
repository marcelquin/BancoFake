package App.Infra.UseCase;

import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;

public class UseCaseTransacaoPost {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPost(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public ResponseEntity<Transacao> novoSaque(String acount, Double valor)
    { return transacaoGateway.novoSaque(acount, valor);}

    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario, Double valor)
    { return transacaoGateway.novoDeposito(acountPagador, acountBeneficiario, valor);}
}
