package APP.Api;

import APP.Domain.Response.Cliente;
import APP.Infra.UseCase.UseCaseClienteDelete;
import APP.Infra.UseCase.UseCaseClienteGet;
import APP.Infra.UseCase.UseCaseClientePost;
import APP.Infra.UseCase.UseCaseClientePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("cliente")
@Tag(name = "Micro serviço Clientes",
        description = "Manipula informações relacionadas a entidade")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CLienteController {

        private final UseCaseClienteGet caseClienteGet;
        private final UseCaseClientePost caseClientePost;
        private final UseCaseClientePut caseClientePut;
        private final UseCaseClienteDelete caseClienteDelete;

        public CLienteController(UseCaseClienteGet caseClienteGet, UseCaseClientePost caseClientePost, UseCaseClientePut caseClientePut, UseCaseClienteDelete caseClienteDelete) {
                this.caseClienteGet = caseClienteGet;
                this.caseClientePost = caseClientePost;
                this.caseClientePut = caseClientePut;
                this.caseClienteDelete = caseClienteDelete;
        }


        @Operation(summary = "Lista Registros da tabela", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @GetMapping("/ListarClientes")
        public ResponseEntity<List<Cliente>> ListarClientes()
        { return caseClienteGet.ListarClientes();}


        @Operation(summary = "Busca Registro da tabela Por Id", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @GetMapping("/BuscarClientesPorId")
        public ResponseEntity<Cliente> BuscarClientesPorId(@RequestParam Long id)
        { return caseClienteGet.BuscarClientesPorId(id);}

        @Operation(summary = "Busca Registro da tabela Por Documento", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @GetMapping("/BuscarClientesPorDocumento")
        public ResponseEntity<Cliente> BuscarClientesPorDocumento(@RequestParam Long documento)
        {return caseClienteGet.BuscarClientesPorDocumento(documento);}

        @Operation(summary = "Salva novo Registro na tabela", method = "POST")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @PostMapping("/NovoCliente")
        public ResponseEntity<Cliente> NovoCliente(@RequestParam String nome,
                                                      @RequestParam String sobrenome,
                                                      @RequestParam Long documento,
                                                      @RequestParam LocalDate dataNascimento,
                                                      String logradouro,
                                                      @RequestParam String numero,
                                                      String bairro,
                                                      String referencia,
                                                      @RequestParam String cep,
                                                      @RequestParam Long prefixo,
                                                      @RequestParam Long telefone,
                                                      @RequestParam String email,
                                                      Double score)
        {return caseClientePost.NovoCliente(nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email, score);}

        @Operation(summary = "Edita Registro na tabela", method = "PUT")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @PutMapping("/EditarCliente")
        public ResponseEntity<Cliente> EditarCliente(@RequestParam Long id,
                                                                @RequestParam String nome,
                                                                @RequestParam String sobrenome,
                                                                @RequestParam Long documento,
                                                                @RequestParam LocalDate dataNascimento,
                                                                String logradouro,
                                                                @RequestParam String numero,
                                                                String bairro,
                                                                String referencia,
                                                                @RequestParam String cep,
                                                                @RequestParam Long prefixo,
                                                                @RequestParam Long telefone,
                                                                @RequestParam String email,
                                                                Double score)
        {return caseClientePut.EditarCliente(id, nome, sobrenome, documento, dataNascimento, logradouro, numero, bairro, referencia, cep, prefixo, telefone, email, score);}


        @Operation(summary = "Deleta Registro na tabela", method = "DELETE")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
                @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
                @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
                @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
        })
        @DeleteMapping("/DeletarClientesPorId")
        public void DeletarClientesPorId(@RequestParam Long id)
        { caseClienteDelete.DeletarClientesPorId(id);}
}
