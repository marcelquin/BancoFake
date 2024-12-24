package App.Api;

import App.Domain.Response.Transacao;
import App.Infra.UseCase.UseCaseTransacaoPost;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private final UseCaseTransacaoPost caseTransacaoPost;

    public TransacaoController(UseCaseTransacaoPost caseTransacaoPost) {
        this.caseTransacaoPost = caseTransacaoPost;
    }

    public ResponseEntity<Transacao> novoSaque(String acount, Double valor)
    { return caseTransacaoPost.novoSaque(acount, valor);}

    public ResponseEntity<Transacao> novoDeposito(String acountPagador, String acountBeneficiario, Double valor)
    { return caseTransacaoPost.novoDeposito(acountPagador, acountBeneficiario, valor);}
}
