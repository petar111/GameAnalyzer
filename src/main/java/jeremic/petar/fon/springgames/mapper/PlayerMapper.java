package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.player.PlayerDTO;
import jeremic.petar.fon.springgames.entity.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PayoffMapper.class})
public interface PlayerMapper {
    PlayerDTO toDto(Player player);

    Player toEntity(PlayerDTO player);
}
