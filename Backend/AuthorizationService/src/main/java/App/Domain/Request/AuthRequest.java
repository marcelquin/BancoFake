package App.Domain.Request;

import java.time.LocalDateTime;

public record AuthRequest(
        Long idTransacao,
        Boolean bloqueio,
        Boolean Ativo,
        Boolean auth,
        LocalDateTime dataAuth
) {
}
