package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.StrategyDTO;
import jeremic.petar.fon.springgames.entity.Strategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StrategyMapper {

    StrategyDTO toDto(Strategy strategy);

    Strategy toEntity(StrategyDTO strategy);
}
