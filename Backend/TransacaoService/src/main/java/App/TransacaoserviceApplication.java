package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransacaoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoserviceApplication.class, args);
	}

}
