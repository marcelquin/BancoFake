package App.Infra.UseCase;

import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;

public class UseCaseTransacaoPost {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPost(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public ResponseEntity<Transacao> novoSaque(String acount, String senhaAutorizacao,Double valor)
    { return transacaoGateway.novoSaque(acount,senhaAutorizacao, valor);}

    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario,String senhaAutorizacao, Double valor)
    { return transacaoGateway.novoDeposito(acountPagador,senhaAutorizacao, acountBeneficiario, valor);}
}
