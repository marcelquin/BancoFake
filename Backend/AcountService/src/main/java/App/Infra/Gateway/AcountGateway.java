package App.Infra.Gateway;

import App.Domain.Response.AcountResponse;
import App.Infra.Persistence.Enum.TIPOACOUNT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AcountGateway {

    public ResponseEntity<List<AcountResponse>> ListarAcounts();

    public ResponseEntity<AcountResponse> BuscarAcountPorId(@RequestParam Long id);

    public ResponseEntity<AcountResponse>BuscarAcountPorAcountNumber(@RequestParam String acountNumber);

    public ResponseEntity<AcountResponse>NovaAcount(@RequestParam String clienteNome,
                                                    @RequestParam String clienteSobrenome,
                                                    @RequestParam String cpfCnpj,
                                                    @RequestParam String telefone,
                                                    @RequestParam String email,
                                                    @RequestParam TIPOACOUNT tipoacount);

    public ResponseEntity<AcountResponse> BloquearAcount(@RequestParam Long id, @RequestParam String justificativa);
}
