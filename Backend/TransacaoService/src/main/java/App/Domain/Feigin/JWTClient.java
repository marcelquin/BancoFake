package App.Domain.Feigin;

import App.Domain.Request.AuthenticationDTO;
import App.Domain.Response.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "jwt-service", url = "http://localhost:8085/auth")
public interface JWTClient {

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data);
}
