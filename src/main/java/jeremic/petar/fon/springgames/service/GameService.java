package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.dto.GameInfoDto;

import java.util.List;

public interface GameService {


    GameDTO findById(Long id);

    List<GameInfoDto> findAllInfo();


    GameDTO save(GameDTO game);

    GameDTO findByName(String name);
}
