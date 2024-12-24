package App.Infra.Gateway;

import App.Domain.Response.Transacao;
import org.springframework.http.ResponseEntity;

public interface TransacaoGateway {


    public ResponseEntity<Transacao> novoSaque(Long documento, Double valor);

    public ResponseEntity<Transacao> novoDeposito(Long documentoPagador, Long documentoBeneficiario, Double valor);


}
