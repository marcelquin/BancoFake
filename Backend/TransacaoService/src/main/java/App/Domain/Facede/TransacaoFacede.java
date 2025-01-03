package App.Domain.Facede;

import App.Domain.Producer.TransacaoProducer;
import App.Domain.Request.AuthRequest;
import org.springframework.stereotype.Service;

@Service
public class TransacaoFacede {

    private final TransacaoProducer producer;


    public TransacaoFacede(TransacaoProducer producer) {
        this.producer = producer;
    }

    public AuthRequest SolicitarProcessamento(AuthRequest authRequest)
    {
        try
        {
            producer.integrarRequest(authRequest);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

}
