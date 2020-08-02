package jeremic.petar.fon.springgames.controller;

import jeremic.petar.fon.springgames.dto.CreatureDTO;
import jeremic.petar.fon.springgames.dto.GameDTO;
import jeremic.petar.fon.springgames.entity.Creature;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.Strategy;
import jeremic.petar.fon.springgames.mapper.CreatureMapper;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.repository.CreatureRepository;
import jeremic.petar.fon.springgames.repository.GameRepository;
import jeremic.petar.fon.springgames.repository.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "home")
@CrossOrigin
public class HomeController {

    private final CreatureRepository creatureRepository;

    private final GameRepository gameRepository;

    private final CreatureMapper creatureMapper;

    private final GameMapper gameMapper;

    private final StrategyRepository strategyRepository;

    @Autowired
    public HomeController(CreatureRepository creatureRepository,
                          GameRepository gameRepository, CreatureMapper creatureMapper,
                          GameMapper gameMapper, StrategyRepository strategyRepository) {
        this.creatureRepository = creatureRepository;
        this.gameRepository = gameRepository;
        this.creatureMapper = creatureMapper;
        this.gameMapper = gameMapper;
        this.strategyRepository = strategyRepository;
    }


    @GetMapping(path = "/get-creature/{id}")
    public ResponseEntity<CreatureDTO> getCreature(@PathVariable Long id){
        Creature creature = creatureRepository.findById(id).orElse(null);

        CreatureDTO result = creatureMapper.toDto(creature);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/creature/insert")
    public ResponseEntity<String> insertGame(@RequestBody CreatureDTO creature){
        Creature creatureEntity = creatureMapper.toEntity(creature);
        creatureRepository.save(creatureEntity);
        return new ResponseEntity<>("Creature is saved", HttpStatus.OK);
    }

    @PostMapping(path = "/game/insert")
    public ResponseEntity<String> insertGame(@RequestBody GameDTO game){
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

        gameRepository.save(gameEntity);

        return new ResponseEntity<>("Game is saved", HttpStatus.OK);
    }




}
