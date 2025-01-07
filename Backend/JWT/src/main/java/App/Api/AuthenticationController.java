package App.Api;



import App.Domain.Response.AuthenticationDTO;
import App.Domain.Response.LoginResponseDTO;
import App.Domain.Response.RegisterDTO;
import App.Infra.Persistence.Entity.User;
import App.Infra.UseCase.UseCaseUserPost;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final UseCaseUserPost caseUserPost;

    public AuthenticationController(UseCaseUserPost caseUserPost) {
        this.caseUserPost = caseUserPost;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data)
    {
        return caseUserPost.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data)
    {
        return caseUserPost.register(data);
    }
}
