package App.Domain.Consumer;

import App.Domain.Client.TransacaoService;
import App.Domain.Request.AuthRequest;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.TIPOTRANSACAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoAuthService {

    private final TransacaoService transacaoService;

    public TransacaoAuthService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @RabbitListener(queues = {"authorization-request-queue"})
    public void checkAcount(@Payload Message message) throws JsonProcessingException {
        String payload = (String) message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AuthRequest dado = mapper.readValue(payload, AuthRequest.class);
        if (dado.bloqueio() == true) {
            throw new IllegalActionException();
        }
        if (dado.Ativo() == false) {
            throw new IllegalActionException();
        }
        AuthRequest response = new AuthRequest(dado.idTransacao(), dado.tipotransacao(), dado.bloqueio(), dado.Ativo(), Boolean.TRUE, LocalDateTime.now());
        if (response.tipotransacao() == TIPOTRANSACAO.SAQUE)
        {transacaoService.FinalizarTransacaoSaque(response);}
        if(response.tipotransacao() == TIPOTRANSACAO.DEPOSITO)
        {transacaoService.FinalizarTransacaoDeposisito(response); }
        if(response.tipotransacao() == TIPOTRANSACAO.ADD_SALDO)
        {transacaoService.FinalizarTransacaoAdicionarSaldo(response);}

    }


}
