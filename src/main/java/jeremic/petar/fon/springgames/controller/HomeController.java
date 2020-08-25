package jeremic.petar.fon.springgames.controller;

import jeremic.petar.fon.springgames.dto.game.GameScoreDto;
import jeremic.petar.fon.springgames.dto.game.GameSessionDto;
import jeremic.petar.fon.springgames.dto.sample.CreatureDTO;
import jeremic.petar.fon.springgames.dto.game.GameDTO;
import jeremic.petar.fon.springgames.entity.*;
import jeremic.petar.fon.springgames.mapper.*;
import jeremic.petar.fon.springgames.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "home")
public class HomeController {

    private final CreatureRepository creatureRepository;

    private final GameRepository gameRepository;

    private final CreatureMapper creatureMapper;

    private final GameMapper gameMapper;

    private final StrategyRepository strategyRepository;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final GameSessionRepository gameSessionRepository;
    private final GameSessionMapper gameSessionMapper;

    private final GameScoreRepository gameScoreRepository;
    private final GameScoreMapper gameScoreMapper;
    private final RankRepository rankRepository;
    private final VerificationStatusRepository verificationStatusRepository;

    @Autowired
    public HomeController(CreatureRepository creatureRepository,
                          GameRepository gameRepository, CreatureMapper creatureMapper,
                          GameMapper gameMapper, StrategyRepository strategyRepository, UserRepository userRepository, UserMapper userMapper, GameSessionRepository gameSessionRepository, GameSessionMapper gameSessionMapper, GameScoreRepository gameScoreRepository, GameScoreMapper gameScoreMapper, RankRepository rankRepository, VerificationStatusRepository verificationStatusRepository) {
        this.creatureRepository = creatureRepository;
        this.gameRepository = gameRepository;
        this.creatureMapper = creatureMapper;
        this.gameMapper = gameMapper;
        this.strategyRepository = strategyRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.gameSessionRepository = gameSessionRepository;
        this.gameSessionMapper = gameSessionMapper;
        this.gameScoreRepository = gameScoreRepository;
        this.gameScoreMapper = gameScoreMapper;
        this.rankRepository = rankRepository;
        this.verificationStatusRepository = verificationStatusRepository;
    }

    @GetMapping("/test/{id}")
    public GameScoreDto test(@PathVariable Long id){

        return gameScoreMapper.toDto(gameScoreRepository.findById(id).get());
    }


    @GetMapping(path = "/get-creature/{id}")
    public ResponseEntity<CreatureDTO> getCreature(@PathVariable Long id){
        Creature creature = creatureRepository.findById(id).orElse(null);

        CreatureDTO result = creatureMapper.toDto(creature);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/all")
    public ResponseEntity<List<User>> allUsers(){

            List<User> result = userRepository.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/game-session/{id}")
    public ResponseEntity<GameSessionDto> findById(@PathVariable Long id){

        GameSessionDto result = gameSessionMapper.toDto(gameSessionRepository.findById(id).orElse(null));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/user/game-session/convert")
    public ResponseEntity<GameSession> convert(@RequestBody GameSessionDto gameSessionDto){

        GameSession result = gameSessionMapper.toEntity(gameSessionDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/creature/insert")
    public ResponseEntity<String> insertGame(@RequestBody CreatureDTO creature){
        Creature creatureEntity = creatureMapper.toEntity(creature);
        creatureRepository.save(creatureEntity);
        return new ResponseEntity<>("Creature is saved", HttpStatus.OK);
    }

    @PostMapping(path = "/creature/update")
    public ResponseEntity<String> updateCreature(@RequestBody CreatureDTO creature){
        Creature creatureEntity = creatureRepository.findById(creature.getId()).get();
        creatureEntity.setName(creature.getName());
        creatureEntity.setDietType(creature.getDietType());
        creatureRepository.save(creatureEntity);
        return new ResponseEntity<>("Creature is updated", HttpStatus.OK);
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

    private GameSessionDto prepareGameSessionDto(GameSession gameSession) {

//        GameSessionDto result = gameSessionMapper.toDto(gameSession);
//
//        Map<String, PlayerMatchDto> players = new HashMap<>();
//
//        gameSession.getPlayers().forEach(playerMatch -> {
//            PlayerMatchDto playerMatchDto = gameSessionMapper.toPlayerMatchDto(playerMatch);
//            Map<Long, Integer> strategyPlayed = new HashMap<>();
//            playerMatch.getStrategyPlayed().forEach(playedStrategy -> {
//                strategyPlayed.put(playedStrategy.getId(), playedStrategy.getTimesPlayed());
//            });
////            playerMatchDto.setStrategyPlayed(strategyPlayed);
//            players.put(playerMatchDto.getPlayerLabel(), playerMatchDto);
//        });
//
//
//        result.setPlayers(players);
//        return result;
        return null;

    }

    private GameSession prepareGameSessionEntity(GameSessionDto gameSession) {

//        GameSession result = gameSessionMapper.toEntity(gameSession);
//
//        Set<PlayerMatch> players = gameSession.getPlayers().values().stream()
//                .flatMap(playerMatchDto -> Stream.of(gameSessionMapper.toPlayerMatchEntity(playerMatchDto))).collect(Collectors.toSet());
//
//        players.forEach(playerMatch -> {
//            Set<PlayedStrategy> playedStrategies = new HashSet<>();
//            gameSession.getPlayers().values().stream()
//                    .filter(playerMatchDto -> playerMatchDto.getPlayer().getId().equals(playerMatch.getPlayer().getId()))
//                    .findFirst().orElseThrow(() -> new NoSuchElementException("Player not found."))
//                    .getStrategyPlayed().forEach(
//                    (strategyId, timesPlayed) -> playedStrategies.add(new PlayedStrategy(timesPlayed, strategyRepository.findById(strategyId).orElseThrow(() -> new NoSuchElementException("Strategy not found."))))
//            );
//            playerMatch.setStrategyPlayed(playedStrategies);
//        });
//
//        User user = userRepository.findById(gameSession.getCreator().getId())
//                .orElseThrow(() -> new NoSuchElementException("User not found."));
//
//        result.setCreator(user);
//        result.setPlayers(players);

//        return result;
        return null;
    }




}
