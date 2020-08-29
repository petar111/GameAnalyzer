package jeremic.petar.fon.springgames.service;

import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.VerificationRequest;
import jeremic.petar.fon.springgames.dto.game.*;

import java.util.List;

public interface GameService {


    GameDTO findById(Long id);

    List<GameInfoDto> findAllInfo();

    GameDTO save(GameDTO game);

    GameDTO findByName(String name);

    GameSessionDto saveGameSession(GameSessionDto gameSession);

    List<GameSessionInfoDto> findAllGameSessionInfoByUsername(String username);

    GameSessionDto findGameSessionById(Long id);

    GameAdviceDto makeGameAdviceById(Long id);

    ExperienceUpdateDto saveGameScore(GameScoreDto gameScoreDto);

    GameVerificationResponseDto attemptVerification(VerificationRequest verificationRequest);
}
