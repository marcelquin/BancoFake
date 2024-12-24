package APP.Infra.UseCase;

import APP.Infra.Gateway.ClienteGateway;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseClienteDelete {

    private final ClienteGateway clienteGateway;

    public UseCaseClienteDelete(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public void DeletarClientesPorId(@RequestParam Long id)
    {clienteGateway.DeletarClientesPorId(id);}


}
