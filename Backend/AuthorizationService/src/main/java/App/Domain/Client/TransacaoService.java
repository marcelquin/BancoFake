package App.Domain.Client;

import App.Domain.Request.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "transacao-service", url = "http://localhost:8083/transacao")
public interface TransacaoService {

    @PutMapping("/FinalizarTransacao")
    public void FinalizarTransacao(AuthRequest authRequest);
}
