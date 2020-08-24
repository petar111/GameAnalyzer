package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.RankDto;
import jeremic.petar.fon.springgames.entity.Rank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RankMapper {
    Rank toEntity(RankDto rankDto);

    RankDto toDto(Rank rank);
}
