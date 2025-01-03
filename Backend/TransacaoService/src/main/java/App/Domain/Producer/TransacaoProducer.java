package App.Domain.Producer;

import App.Domain.Request.AuthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransacaoProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void integrarRequest (AuthRequest response) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "authorization-request-queue",
                "authorization-request-queue-key",
                //response
                objectMapper.writeValueAsString(response)
        );
    }



}
