package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GameMapper.class, UserMapper.class})
public interface GameScoreMapper {
    Game toEntity(GameDTO gameDTO);

    GameDTO toDto(Game game);

}
