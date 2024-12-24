package APP.Util;

import APP.Domain.Response.Cliente;
import APP.Infra.Persistence.Entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteEntity DtoToEntity(Cliente cliente);

    Cliente EntityToDto(ClienteEntity clienteEntity);
}
