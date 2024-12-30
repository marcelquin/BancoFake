package App.Domain.Consumer;

import App.Domain.Facede.TransacaoFacede;
import App.Domain.Request.AuthRequest;
import App.Infra.Exceptions.IllegalActionException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransacaoAuthService {

    private final TransacaoFacede transacaoFacede;

    public TransacaoAuthService(TransacaoFacede transacaoFacede) {
        this.transacaoFacede = transacaoFacede;
    }

    @RabbitListener(queues = {"transacao-auth-request"})
    public AuthRequest checkAcount(@Payload AuthRequest authRequest)
    {
        AuthRequest dado = authRequest;
        System.out.println(dado.Ativo());
        if(authRequest.bloqueio() == true){throw new IllegalActionException();}
        if(authRequest.Ativo() == false){throw new IllegalActionException();}
        AuthRequest response = new AuthRequest(authRequest.idTransacao(),authRequest.bloqueio(),authRequest.Ativo(),Boolean.TRUE, LocalDateTime.now());
        transacaoFacede.Sucess(response);
        return dado;
    }


}
