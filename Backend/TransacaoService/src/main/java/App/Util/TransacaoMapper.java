package App.Util;

import App.Domain.Response.Transacao;
import App.Infra.Persistence.Entity.TransacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    TransacaoEntity DtoToEntity(Transacao transacao);

    Transacao EntitytoDto(TransacaoEntity transacaoEntity);


}
