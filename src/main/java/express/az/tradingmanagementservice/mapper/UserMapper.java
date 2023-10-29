package express.az.tradingmanagementservice.mapper;

import express.az.tradingmanagementservice.model.dto.request.UserRequestDto;
import express.az.tradingmanagementservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "isEnable", ignore = true),
            @Mapping(target = "tokens", ignore = true)
    })
    User userDtoToUser(UserRequestDto userRequestDto);

}

