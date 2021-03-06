package jeremic.petar.fon.springgames.mapper;


import jeremic.petar.fon.springgames.dto.sample.CreatureDTO;
import jeremic.petar.fon.springgames.dto.sample.PowerStatDto;
import jeremic.petar.fon.springgames.entity.Creature;
import jeremic.petar.fon.springgames.entity.PowerStat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StatMapper.class})
public interface CreatureMapper {

    CreatureDTO toDto(Creature creature);

    Creature toEntity(CreatureDTO creatureDTO);

    PowerStatDto toPowerStatDto(PowerStat powerStat);

    PowerStat toPowerStatEntity(PowerStatDto powerStatDto);

//    @Mapping(target = "powerStats", ignore = true)
//    CreatureDTO toBasicsDto(Creature creature);
//
//    @Mapping(target = "powerStats", ignore = true)
//    Creature toBasicsEntity(CreatureDTO creatureDTO);

}
