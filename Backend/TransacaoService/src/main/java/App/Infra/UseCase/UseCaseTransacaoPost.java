package App.Infra.UseCase;

import App.Domain.Response.Transacao;
import App.Infra.Gateway.TransacaoGateway;
import org.springframework.http.ResponseEntity;

public class UseCaseTransacaoPost {

    private final TransacaoGateway transacaoGateway;

    public UseCaseTransacaoPost(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public ResponseEntity<Transacao> novoSaque(Long documento, Double valor)
    { return transacaoGateway.novoSaque(documento, valor);}

    public ResponseEntity<Transacao> novoDeposito(Long documentoPagador, Long documentoBeneficiario, Double valor)
    { return transacaoGateway.novoDeposito(documentoPagador, documentoBeneficiario, valor);}
}
