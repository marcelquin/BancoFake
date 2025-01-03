package App.Infra.Gateway;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransacaoGateway {


    public ResponseEntity<Transacao> novoSaque(String acount, Double valor);

    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario, Double valor);

    public void FinalizarTransacao(@RequestBody AuthRequest authRequest);
}
