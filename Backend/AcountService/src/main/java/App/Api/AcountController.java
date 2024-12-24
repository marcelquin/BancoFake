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
    public ResponseEntity<AcountResponse>NovaAcount(@RequestParam String clienteNome,
                                                    @RequestParam String clienteSobrenome,
                                                    @RequestParam Long documento,
                                                    @RequestParam String telefone,
                                                    @RequestParam String email,
                                                    @RequestParam TIPOACOUNT tipoacount)
    { return caseAcountPost.NovaAcount(clienteNome, clienteSobrenome, documento, telefone, email, tipoacount);}

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
