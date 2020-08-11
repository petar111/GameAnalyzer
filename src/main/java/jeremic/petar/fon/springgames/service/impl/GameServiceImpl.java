package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.dto.game.GameInfoDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionInfoDto;
import jeremic.petar.fon.springgames.dto.player.PlayerMatchDto;
import jeremic.petar.fon.springgames.entity.*;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.mapper.GameSessionMapper;
import jeremic.petar.fon.springgames.repository.GameRepository;
import jeremic.petar.fon.springgames.repository.GameSessionRepository;
import jeremic.petar.fon.springgames.repository.StrategyRepository;
import jeremic.petar.fon.springgames.repository.UserRepository;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    private final StrategyRepository strategyRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GameSessionMapper gameSessionMapper;
    private final GameSessionRepository gameSessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameServiceImpl(StrategyRepository strategyRepository,
                           GameRepository gameRepository,
                           GameMapper gameMapper,
                           GameSessionMapper gameSessionMapper,
                           GameSessionRepository gameSessionRepository,
                           UserRepository userRepository) {
        this.strategyRepository = strategyRepository;
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameSessionMapper = gameSessionMapper;
        this.gameSessionRepository = gameSessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GameDTO findById(Long id) {
        return gameMapper.toDto(gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Game with id %s doesnt exist.", id))));
    }

    @Override
    public List<GameInfoDto> findAllInfo() {
        return gameMapper.toListGameInfoDto(gameRepository.findAll());
    }

    @Override
    public GameDTO save(GameDTO game) {
        Game gameEntity = gameMapper.toEntity(game);

        List<Strategy> savedStrategies = strategyRepository.saveAll(gameEntity.getStrategies());

        gameEntity.setStrategies(savedStrategies);

        gameEntity.getPlayers()
                .forEach(player -> player.getPayoffs()
                        .forEach(payoff -> savedStrategies
                                .forEach(strategy -> {
                                    if (strategy.getName().equals(payoff.getPlayedStrategy().getName()))
                                        payoff.setPlayedStrategy(strategy);
                                    if (strategy.getName().equals(payoff.getOpposingStrategy().getName()))
                                        payoff.setOpposingStrategy(strategy);
                                })));


        gameEntity.setCreator(userRepository.findById(gameEntity.getCreator().getId())
                .orElseThrow(() -> new RuntimeException("User not found.")));


        Game savedGame = gameRepository.save(gameEntity);

        return gameMapper.toDto(savedGame);
    }

    @Override
    public GameDTO findByName(String name) {
        return gameMapper.toDto(gameRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Game with name not found")
        )); // TODO: Case when there is more than one game by that name
    }

    @Override
    public GameSessionDto saveGameSession(GameSessionDto gameSession) {

        GameSession gameSessionEntity = gameSessionMapper.toEntity(gameSession);
        gameSessionEntity.setCreator(userRepository.findById(gameSessionEntity.getCreator().getId())
                .orElseThrow(() -> new NoSuchElementException("User not found.")));


        GameSession savedGameSession = gameSessionRepository.save(gameSessionEntity);
        return gameSessionMapper.toDto(savedGameSession);
    }

    @Override
    public List<GameSessionInfoDto> findAllGameSessionInfoByUsername(String username) {
        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        List<GameSession> gameSessionList = gameSessionRepository.findAllByCreator(creator);

        return gameSessionMapper.toGameSessionInfoDtoList(gameSessionList);
    }


}
