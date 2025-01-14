package App.Domain.Request;

import App.Infra.Persistence.Enum.TIPOTRANSACAO;

import java.time.LocalDateTime;

public record AuthRequest(
        Long idTransacao,
        TIPOTRANSACAO tipotransacao,
        Boolean bloqueio,
        Boolean Ativo
) {
}
