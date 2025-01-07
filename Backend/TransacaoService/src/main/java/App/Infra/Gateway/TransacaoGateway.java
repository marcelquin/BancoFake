package App.Infra.Gateway;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.AcountResponse;
import App.Domain.Response.Transacao;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Persistence.Entity.TransacaoEntity;
import App.Infra.Persistence.Enum.STATUSTRANSACAO;
import App.Infra.Persistence.Enum.TIPOTRANSACAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public interface TransacaoGateway {


    public ResponseEntity<Transacao> novoSaque(@RequestParam String acount,@RequestParam String senhaAutorizacao,@RequestParam Double valor);

    public ResponseEntity<Transacao> novoDeposito(@RequestParam String acountPagador,@RequestParam String senhaAutorizacao,@RequestParam String acountBeneficiario, @RequestParam Double valor);

    public void FinalizarTransacaoSaque(@RequestBody AuthRequest authRequest);

    public void FinalizarTransacaoDeposisito(@RequestBody AuthRequest authRequest);

    public void FinalizarTransacaoAdicionarSaldo(@RequestBody AuthRequest authRequest);

    public ResponseEntity<Transacao> AdicionarSaldo(@RequestParam String acountPagador,@RequestParam String senhaAutenticacao,@RequestParam Double valor);

}
