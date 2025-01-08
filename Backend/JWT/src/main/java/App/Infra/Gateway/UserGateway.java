package App.Infra.Gateway;

import App.Domain.Response.AuthenticationDTO;
import App.Domain.Response.LoginResponseDTO;
import App.Domain.Response.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserGateway {

    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data);

    public ResponseEntity register(@RequestBody @Valid RegisterDTO data);

    public ResponseEntity registerLoginMaster(@RequestParam String user, @RequestParam String password);
}
