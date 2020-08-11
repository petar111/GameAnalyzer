package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.game.GameSessionDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionInfoDto;
import jeremic.petar.fon.springgames.entity.GameSession;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {GameMapper.class, PlayerMatchMapper.class, StrategyMapper.class, PlayerMapper.class, UserMapper.class})
public interface GameSessionMapper {

    GameSession toEntity(GameSessionDto gameSessionDto);

    GameSessionDto toDto(GameSession gameSession);

    GameSessionInfoDto toGameSessionInfoDto(GameSession gameSession);

    List<GameSessionInfoDto> toGameSessionInfoDtoList(List<GameSession> gameSessionList);


}
