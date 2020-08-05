package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.dto.GameInfoDto;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.Strategy;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.repository.GameRepository;
import jeremic.petar.fon.springgames.repository.StrategyRepository;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final StrategyRepository strategyRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Autowired
    public GameServiceImpl(StrategyRepository strategyRepository, GameRepository gameRepository, GameMapper gameMapper) {
        this.strategyRepository = strategyRepository;
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public GameDTO findById(Long id) {
        return gameMapper.toDto(gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Game with id %s doesnt exist.", id))));
    }

    @Override
    public List<GameInfoDto> findAllInfo() {
        return gameMapper.toListGameInfoDto(gameRepository.findAll()); //TODO Case when there is no games.
    }

    @Override
    public GameDTO save(GameDTO game) {
        Game gameEntity = gameMapper.toEntity(game);

        List<Strategy> savedStrategies = strategyRepository.saveAll(gameEntity.getStrategies());

        gameEntity.setStrategies(savedStrategies);

        gameEntity.getPlayers()
                .forEach(player -> player.getPayoffs()
                        .forEach(payoff -> savedStrategies
                                .forEach(strategy -> {if(strategy.getName().equals(payoff.getPlayedStrategy().getName()))
                                    payoff.setPlayedStrategy(strategy);
                                    if(strategy.getName().equals(payoff.getOpposingStrategy().getName()))
                                        payoff.setOpposingStrategy(strategy);  } )));



        Game savedGame = gameRepository.save(gameEntity);
        return gameMapper.toDto(savedGame);
    }

    @Override
    public GameDTO findByName(String name) {
        return gameMapper.toDto(gameRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Game with name not found")
        )); // TODO: Case when there is more than one game by that name
    }


}
