package jeremic.petar.fon.springgames.mapper;


import jeremic.petar.fon.springgames.dto.CreatureDTO;
import jeremic.petar.fon.springgames.dto.PowerStatDto;
import jeremic.petar.fon.springgames.entity.Creature;
import jeremic.petar.fon.springgames.entity.PowerStat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StatMapper.class})
public interface CreatureMapper {

    CreatureDTO toDto(Creature creature);

    Creature toEntity(CreatureDTO creatureDTO);

    PowerStatDto toPowerStatDto(PowerStat powerStat);

    PowerStat toPowerStatEntity(PowerStatDto powerStatDto);

}
