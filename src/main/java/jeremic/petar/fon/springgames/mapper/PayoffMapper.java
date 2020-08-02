package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.dto.PayoffDTO;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.Payoff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StrategyMapper.class})
public interface PayoffMapper {
    PayoffDTO toDto(Payoff payoff);

    Payoff toEntity(PayoffDTO payoff);
}
