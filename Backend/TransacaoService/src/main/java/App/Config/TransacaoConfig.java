package App.Config;

import App.Infra.Gateway.TransacaoGateway;
import App.Infra.UseCase.UseCaseTransacaoPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransacaoConfig {

    @Bean
    UseCaseTransacaoPost useCaseTransacaoPost(TransacaoGateway transacaoGateway)
    { return new UseCaseTransacaoPost(transacaoGateway);}

}
