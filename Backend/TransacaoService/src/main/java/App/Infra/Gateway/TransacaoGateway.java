package App.Infra.Gateway;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface TransacaoGateway {


    public ResponseEntity<Transacao> novoSaque(@RequestParam String acount,@RequestParam String senhaAutorizacao,@RequestParam Double valor);

    public ResponseEntity<Transacao> novoDeposito(@RequestParam String acountPagador,@RequestParam String senhaAutorizacao,@RequestParam String acountBeneficiario, @RequestParam Double valor);

    public void FinalizarTransacao(@RequestBody AuthRequest authRequest);
}
