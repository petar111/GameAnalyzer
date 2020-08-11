package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.PlayedStrategyDto;
import jeremic.petar.fon.springgames.dto.player.PlayerMatchDto;
import jeremic.petar.fon.springgames.entity.PlayedStrategy;
import jeremic.petar.fon.springgames.entity.PlayerMatch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class, StrategyMapper.class})
public interface PlayerMatchMapper {

    PlayerMatch toEntity(PlayerMatchDto playerMatchDto);

    PlayerMatchDto toDto(PlayerMatch playerMatch);

    PlayedStrategyDto toPlayedStrategyDto(PlayedStrategy playedStrategy);

    PlayedStrategy toPlayedStrategyEntity(PlayedStrategyDto playedStrategyDto);

    List<PlayedStrategyDto> toPlayedStrategyListDto(List<PlayedStrategy> playedStrategies);

    List<PlayedStrategy> toPlayedStrategyListEntity(List<PlayedStrategyDto> playedStrategiesDto);

}
