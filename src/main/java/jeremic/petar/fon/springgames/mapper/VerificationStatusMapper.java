package jeremic.petar.fon.springgames.mapper;

import jeremic.petar.fon.springgames.dto.VerificationStatusDto;
import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.VerificationStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationStatusMapper {
    VerificationStatus toEntity(VerificationStatusDto verificationStatusDto);

    VerificationStatusDto toDto(VerificationStatus verificationStatus);
}
