package App.Config;

import App.Infra.Gateway.AcountGateway;
import App.Infra.UseCase.Account.UseCaseAcountPost;
import App.Infra.UseCase.Account.UseCaseAcountPut;
import App.Infra.UseCase.Account.UseCaseAcounteGet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcountConfig {

    @Bean
    UseCaseAcounteGet useCaseAcounteGet(AcountGateway acountGateway)
    { return new UseCaseAcounteGet(acountGateway);}

    @Bean
    UseCaseAcountPost useCaseAcountPost(AcountGateway acountGateway)
    { return new UseCaseAcountPost(acountGateway);}

    @Bean
    UseCaseAcountPut useCaseAcountPut(AcountGateway acountGateway)
    { return new UseCaseAcountPut(acountGateway);}
}
