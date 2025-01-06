package App.Api;

import App.Domain.Response.AcountResponse;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import App.Infra.UseCase.Account.UseCaseAcountPost;
import App.Infra.UseCase.Account.UseCaseAcountPut;
import App.Infra.UseCase.Account.UseCaseAcounteGet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("acountservice")
@Tag(name = "acount",
        description = "Manipula dados referente a entidade"
)
public class AcountController {

    private final UseCaseAcounteGet caseAcounteGet;
    private final UseCaseAcountPost caseAcountPost;
    private final UseCaseAcountPut caseAcountPut;


    public AcountController(UseCaseAcounteGet caseAcounteGet, UseCaseAcountPost caseAcountPost, UseCaseAcountPut caseAcountPut) {
        this.caseAcounteGet = caseAcounteGet;
        this.caseAcountPost = caseAcountPost;
        this.caseAcountPut = caseAcountPut;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarAcounts")
    public ResponseEntity<List<AcountResponse>> ListarAcounts()
    { return caseAcounteGet.ListarAcounts();}


    @Operation(summary = "Busca Registro da tabela por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarAcountPorId")
    public ResponseEntity<AcountResponse> BuscarAcountPorId(@RequestParam Long id)
    { return caseAcounteGet.BuscarAcountPorId(id);}

    @Operation(summary = "Busca Registro da tabela por Acount", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarAcountPorAcountNumber")
    public ResponseEntity<AcountResponse>BuscarAcountPorAcountNumber(@RequestParam String acountNumber)
    { return caseAcounteGet.BuscarAcountPorAcountNumber(acountNumber);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovaAcount")
    public ResponseEntity<AcountResponse>NovaAcount(@RequestParam String nome,
                                                    @RequestParam String sobrenome,
                                                    @RequestParam Long documento,
                                                    @RequestParam LocalDate dataNascimento,
                                                    @RequestParam String logradouro,
                                                    @RequestParam String numero,
                                                    @RequestParam String bairro,
                                                    @RequestParam String referencia,
                                                    @RequestParam String cep,
                                                    @RequestParam Long prefixo,
                                                    @RequestParam Long telefone,
                                                    @RequestParam String email,
                                                    @RequestParam Double score,
                                                    @RequestParam TIPOACOUNT tipoacount)
    { return caseAcountPost.NovaAcount(nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email, score, tipoacount);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/SalvarAlteracao")
    public ResponseEntity<AcountResponse> SalvarAlteracao(@RequestBody AcountResponse acountResponse)
    {return caseAcountPost.SalvarAlteracao(acountResponse);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarLimite")
    public ResponseEntity<AcountResponse> AlterarLimite(@RequestParam Long id, @RequestParam Double novoLimite)
    { return caseAcountPut.alterarLimite(id, novoLimite);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/BloquearAcount")
    public ResponseEntity<AcountResponse> BloquearAcount(@RequestParam Long id, @RequestParam String justificativa)
    { return caseAcountPut.BloquearAcount(id, justificativa);}


}
