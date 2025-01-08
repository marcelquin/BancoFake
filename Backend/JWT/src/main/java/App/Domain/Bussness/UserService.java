package App.Domain.Bussness;

import App.Domain.Response.AuthenticationDTO;
import App.Domain.Response.LoginResponseDTO;
import App.Domain.Response.RegisterDTO;
import App.Infra.Gateway.UserGateway;
import App.Infra.Persistence.Entity.User;
import App.Infra.Persistence.Enum.UserRole;
import App.Infra.Persistence.Repository.UserRepository;
import App.Util.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService implements UserGateway {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    public UserService(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data)
    {
        try
        {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());
            LoginResponseDTO response = new LoginResponseDTO(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity register(RegisterDTO data)
    {
        try
        {
            if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.login(), encryptedPassword, data.role());
            this.repository.save(newUser);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity registerLoginMaster(String user, String password)
    {
        try
        {
            if(this.repository.findByLogin(user) != null) return ResponseEntity.badRequest().build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
            User newUser = new User(user, encryptedPassword, UserRole.ADMIN);
            this.repository.save(newUser);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
        return null;
    }


}
