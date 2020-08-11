package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.sample.StatDto;
import jeremic.petar.fon.springgames.dto.sample.StatTypeDto;
import jeremic.petar.fon.springgames.entity.Stat;
import jeremic.petar.fon.springgames.entity.StatType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatMapper {

    StatDto toDto(Stat stat);

    Stat toEntity(StatDto stat);

    StatTypeDto toStatTypeDto(StatType statType);

    StatType toStatTypeEntity(StatTypeDto statType);
}
