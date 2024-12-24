package App.Infra.Gateway;

import App.Domain.Response.Transacao;
import org.springframework.http.ResponseEntity;

public interface TransacaoGateway {


    public ResponseEntity<Transacao> novoSaque(String acount, Double valor);

    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario, Double valor);


}
