package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RankMapper.class})
public interface UserMapper {

    @Mapping(target = "password", expression = "java(null)")
    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    User toEntity(UserDto userDto);

}
