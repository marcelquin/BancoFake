package App.Api;

import App.Domain.Response.Transacao;
import App.Infra.UseCase.UseCaseTransacaoPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacao")
@Tag(name = "transaçao service",
        description = "Manipula dados referente a entidade"
)
public class TransacaoController {

    private final UseCaseTransacaoPost caseTransacaoPost;

    public TransacaoController(UseCaseTransacaoPost caseTransacaoPost) {
        this.caseTransacaoPost = caseTransacaoPost;
    }

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/novoSaque")
    public ResponseEntity<Transacao> novoSaque(String acount,String senhaAutorizacao, Double valor)
    { return caseTransacaoPost.novoSaque(acount,senhaAutorizacao, valor);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/novoDeposito")
    public ResponseEntity<Transacao> novoDeposito(String acountPagador,String senhaAutorizacao, String acountBeneficiario, Double valor)
    { return caseTransacaoPost.novoDeposito(acountPagador, senhaAutorizacao,acountBeneficiario, valor);}
}
