package App.Infra.UseCase;

import App.Domain.Response.AuthenticationDTO;
import App.Domain.Response.LoginResponseDTO;
import App.Domain.Response.RegisterDTO;
import App.Infra.Gateway.UserGateway;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class UseCaseUserPost {

    private final UserGateway userGateway;

    public UseCaseUserPost(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data)
    {return userGateway.login(data);}

    public ResponseEntity register(@RequestBody @Valid RegisterDTO data)
    { return userGateway.register(data);}
}
