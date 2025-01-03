package App.Domain.Feigin;

import App.Domain.Response.AcountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Acount-service", url = "http://localhost:8082/acountservice")
public interface AcountClient {


    @GetMapping("/BuscarAcountPorAcountNumber")
    public ResponseEntity<AcountResponse> BuscarAcountPorAcountNumber(@RequestParam String acountNumber);

    @PostMapping("/SalvarAlteracao")
    public ResponseEntity<AcountResponse> SalvarAlteracao(@RequestBody AcountResponse acountResponse);
}
