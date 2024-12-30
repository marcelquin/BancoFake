package App.Infra.Gateway;

import App.Domain.Response.AcountResponse;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface AcountGateway {

    public ResponseEntity<List<AcountResponse>> ListarAcounts();

    public ResponseEntity<AcountResponse> BuscarAcountPorId(@RequestParam Long id);

    public ResponseEntity<AcountResponse>BuscarAcountPorAcountNumber(@RequestParam String acountNumber);

    public ResponseEntity<AcountResponse>NovaAcount(@RequestParam String nome,
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
                                                    Double score,
                                                    @RequestParam TIPOACOUNT tipoacount);

    public ResponseEntity<AcountResponse> SalvarAlteracao(@RequestBody AcountResponse acountResponse);

    public ResponseEntity<AcountResponse> BloquearAcount(@RequestParam Long id, @RequestParam String justificativa);
}
