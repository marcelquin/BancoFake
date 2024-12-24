package APP.Infra.UseCase;

import APP.Domain.Response.Cliente;
import APP.Infra.Gateway.ClienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseClienteGet {

    private final ClienteGateway clienteGateway;

    public UseCaseClienteGet(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public ResponseEntity<List<Cliente>> ListarClientes()
    { return clienteGateway.ListarClientes();}
    public ResponseEntity<Cliente> BuscarClientesPorId(@RequestParam Long id)
    {return clienteGateway.BuscarClientesPorId(id);}

    public ResponseEntity<Cliente> BuscarClientesPorDocumento(@RequestParam Long documento)
    {return clienteGateway.BuscarClientesPorDocumento(documento);}
}
