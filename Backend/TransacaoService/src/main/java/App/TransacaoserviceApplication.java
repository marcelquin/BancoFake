package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
@OpenAPIDefinition(info = @Info(title = "Transação Service",
		version = "1",
		description = "Realiza a transação de valores financeiros."))
public class TransacaoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoserviceApplication.class, args);
	}

}
