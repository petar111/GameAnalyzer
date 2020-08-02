package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.entity.Game;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StrategyMapper.class, PlayerMapper.class})
public interface GameMapper {
    GameDTO toDto(Game game);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "externalInfo", target = "externalInfo")
    Game toEntity(GameDTO game);
}
