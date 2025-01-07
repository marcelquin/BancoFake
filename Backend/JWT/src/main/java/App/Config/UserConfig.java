package App.Config;

import App.Infra.Gateway.UserGateway;
import App.Infra.UseCase.UseCaseUserPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UseCaseUserPost useCaseUserPost(UserGateway userGateway)
    {return new UseCaseUserPost(userGateway);}
}
