package App.Api;

import App.Domain.Request.AuthRequest;
import App.Domain.Response.Transacao;
import App.Infra.UseCase.UseCaseTransacaoPost;
import App.Infra.UseCase.UseCaseTransacaoPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacao")
@Tag(name = "transaçao service",
        description = "Manipula dados referente a entidade"
)
public class TransacaoController {

    private final UseCaseTransacaoPost caseTransacaoPost;
    private final UseCaseTransacaoPut caseTransacaoPut;

    public TransacaoController(UseCaseTransacaoPost caseTransacaoPost, UseCaseTransacaoPut caseTransacaoPut) {
        this.caseTransacaoPost = caseTransacaoPost;
        this.caseTransacaoPut = caseTransacaoPut;
    }

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/novoSaque")
    public ResponseEntity<Transacao> novoSaque(@RequestParam String acount, @RequestParam String senhaAutorizacao, @RequestParam Double valor)
    { return caseTransacaoPost.novoSaque(acount,senhaAutorizacao, valor);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/novoDeposito")
    public ResponseEntity<Transacao> novoDeposito(@RequestParam String acountPagador,@RequestParam String senhaAutorizacao,@RequestParam String acountBeneficiario, @RequestParam Double valor)
    { return caseTransacaoPost.novoDeposito(acountPagador,senhaAutorizacao, acountBeneficiario, valor);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/FinalizarTransacaoSaque")
    public void FinalizarTransacaoSaque(@RequestBody AuthRequest authRequest)
    {
       caseTransacaoPut.FinalizarTransacaoSaque(authRequest);
    }

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/FinalizarTransacaoDeposisito")
    public void FinalizarTransacaoDeposisito(@RequestBody AuthRequest authRequest)
    {caseTransacaoPut.FinalizarTransacaoDeposisito(authRequest);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/FinalizarTransacaoAdicionarSaldo")
    public void FinalizarTransacaoAdicionarSaldo(@RequestBody AuthRequest authRequest)
    {caseTransacaoPut.FinalizarTransacaoAdicionarSaldo(authRequest); }

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdicionarSaldo")
    public ResponseEntity<Transacao> AdicionarSaldo(@RequestParam String acountPagador, @RequestParam String senhaAutenticacao, @RequestParam Double valor)
    {return caseTransacaoPut.AdicionarSaldo(acountPagador, senhaAutenticacao, valor);}

}
