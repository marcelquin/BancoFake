package App.Util;

import App.Domain.Response.AcountResponse;
import App.Infra.Persistence.Entity.AcountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AcountMapper {


    AcountEntity DtoToEntity(AcountResponse acountResponse);

    AcountResponse EntityToDto(AcountEntity acount);

}
