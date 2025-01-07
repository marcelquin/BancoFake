package App.Domain.Client;

import App.Domain.Request.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transacao-service", url = "http://localhost:8083/transacao")
public interface TransacaoService {

    @PutMapping("/FinalizarTransacaoSaque")
    public void FinalizarTransacaoSaque(@RequestBody AuthRequest authRequest);
    @PutMapping("/FinalizarTransacaoDeposisito")
    public void FinalizarTransacaoDeposisito(@RequestBody AuthRequest authRequest);
    @PutMapping("/FinalizarTransacaoAdicionarSaldo")
    public void FinalizarTransacaoAdicionarSaldo(@RequestBody AuthRequest authRequest);
}
