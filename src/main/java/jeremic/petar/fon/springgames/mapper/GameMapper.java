package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StrategyMapper.class, PlayerMapper.class, UserMapper.class})
public interface GameMapper {



    GameDTO toDto(Game game);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "externalInfo", target = "externalInfo")
    Game toEntity(GameDTO game);

    @Mapping(target = "creatorUsername", source = "creator.username")
    GameInfoDto toGameInfoDto(Game game);


    List<GameInfoDto> toListGameInfoDto (List<Game> games);

}
