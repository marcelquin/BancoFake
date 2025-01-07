package App.Domain.Response;


import App.Infra.Persistence.Enum.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
