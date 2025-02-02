package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Micro serviço Acount",
		version = "1",
		description = "Responsavel pela gerencia de informações de contas"))
@EnableFeignClients
public class AcounteserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcounteserviceApplication.class, args);
	}

}
