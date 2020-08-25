package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.dto.game.GameScoreDto;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.GameScore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GameMapper.class, UserMapper.class})
public interface GameScoreMapper {
    GameScore toEntity(GameScoreDto gameScoreDto);

    GameScoreDto toDto(GameScore gameScore);

}
