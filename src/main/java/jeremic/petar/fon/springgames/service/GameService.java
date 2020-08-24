package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionInfoDto;

import java.util.List;

public interface GameService {


    GameDTO findById(Long id);

    List<GameInfoDto> findAllInfo();

    GameDTO save(GameDTO game);

    GameDTO findByName(String name);

    GameSessionDto saveGameSession(GameSessionDto gameSession);

    List<GameSessionInfoDto> findAllGameSessionInfoByUsername(String username);

    GameSessionDto findGameSessionById(Long id);
}
