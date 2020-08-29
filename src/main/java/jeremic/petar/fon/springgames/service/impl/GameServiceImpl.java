package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.dto.ExperienceUpdateDto;
import jeremic.petar.fon.springgames.dto.VerificationRequest;
import jeremic.petar.fon.springgames.dto.game.*;
import jeremic.petar.fon.springgames.entity.*;
import jeremic.petar.fon.springgames.exception.GameNotFoundException;
import jeremic.petar.fon.springgames.exception.PayoffNotFoundException;
import jeremic.petar.fon.springgames.exception.PlayerNotFoundException;
import jeremic.petar.fon.springgames.exception.VerificationStatusNotFoundException;
import jeremic.petar.fon.springgames.exception.user.UserNotFoundException;
import jeremic.petar.fon.springgames.mapper.GameMapper;
import jeremic.petar.fon.springgames.mapper.GameScoreMapper;
import jeremic.petar.fon.springgames.mapper.GameSessionMapper;
import jeremic.petar.fon.springgames.mapper.UserMapper;
import jeremic.petar.fon.springgames.repository.*;
import jeremic.petar.fon.springgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final StrategyRepository strategyRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final GameSessionMapper gameSessionMapper;
    private final GameSessionRepository gameSessionRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GameScoreRepository gameScoreRepository;
    private final GameScoreMapper gameScoreMapper;
    private final VerificationStatusRepository verificationStatusRepository;

    @Autowired
    public GameServiceImpl(StrategyRepository strategyRepository,
                           GameRepository gameRepository,
                           GameMapper gameMapper,
                           GameSessionMapper gameSessionMapper,
                           GameSessionRepository gameSessionRepository,
                           UserRepository userRepository, UserMapper userMapper, GameScoreRepository gameScoreRepository, GameScoreMapper gameScoreMapper, VerificationStatusRepository verificationStatusRepository) {
        this.strategyRepository = strategyRepository;
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameSessionMapper = gameSessionMapper;
        this.gameSessionRepository = gameSessionRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.gameScoreRepository = gameScoreRepository;
        this.gameScoreMapper = gameScoreMapper;
        this.verificationStatusRepository = verificationStatusRepository;
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
        gameEntity.setVerificationStatus(verificationStatusRepository.findByName("NOT_VERIFIED")
                .orElseThrow(
                        () -> new VerificationStatusNotFoundException("Verification status with name not found.")
                ));

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

    @Override
    public GameSessionDto findGameSessionById(Long id) {
        return gameSessionMapper.toDto(gameSessionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Game session not found.")));
    }

    @Override
    public GameAdviceDto makeGameAdviceById(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game with id not found") //TODO add string constants
        );
        GameAdviceDto result = new GameAdviceDto();
        Set<Payoff> nashEqiilibria = findNashEquilibria(game);
        result.setNashEquilibria(nashEqiilibria);
        return result;
    }

    @Override
    public ExperienceUpdateDto saveGameScore(GameScoreDto gameScoreDto) {
        GameScore savedGameScore = gameScoreRepository.save(gameScoreMapper.toEntity(gameScoreDto));
        ExperienceUpdateDto result = new ExperienceUpdateDto();
        StringBuilder responseMessage = new StringBuilder("");
        responseMessage.append("Game score saved. ");
        if(isGameVerified(savedGameScore.getGame())){
            result.setExperience(calculateExperience(savedGameScore));
        }else {
            responseMessage.append("Because the game is not verified you did not gain any experience.");
        }
        result.setMessage(responseMessage.toString());
        return result;
    }

    @Override
    public GameVerificationResponseDto attemptVerification(VerificationRequest verificationRequest) {
        Game game = gameRepository.findById(verificationRequest.getGameId()).orElseThrow(
                () -> new GameNotFoundException("Game with id not found.")
        );
        User user = userRepository.findById(verificationRequest.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User with id not found")
        );
        VerificationStatus statusRejected = verificationStatusRepository.findByName("REJECTED")
                .orElseThrow(
                        () -> new VerificationStatusNotFoundException("Verification status with name not found.")
                );
        VerificationStatus statusVerified = verificationStatusRepository.findByName("VERIFIED")
                .orElseThrow(
                        () -> new VerificationStatusNotFoundException("Verification status with name not found.")
                );

        GameVerificationResponseDto result = new GameVerificationResponseDto();

        if(user.getNumberOfVerifiedGames() >= user.getRank().getVerifiedGamesMax()){
          game.setVerificationStatus(statusRejected);
          result.setSignal("GAME_REJECTED");
          result.setMessage("Game has been rejected.");

        }else{
            game.setVerificationStatus(statusVerified);
            user.setNumberOfVerifiedGames(user.getNumberOfVerifiedGames() + 1);
            result.setSignal("GAME_VERIFIED");
            result.setMessage("Game has been verified.");
        }
        Game savedGame = gameRepository.save(game);
        User savedUser = userRepository.save(user);


        result.setGame(gameMapper.toGameInfoDto(savedGame));
        result.setUser(userMapper.toDto(savedUser));

        return result;
    }

    private int calculateExperience(GameScore savedGameScore) {
        int result = calculateExperienceOnHighScore(savedGameScore);
        return result;
    }

    private int calculateExperienceOnHighScore(GameScore savedGameScore) {
        List<GameScore> topHighScores = gameScoreRepository.findAllByNumberOfRoundsAndGameOrderByTotalPayoffDesc(savedGameScore.getNumberOfRounds(), savedGameScore.getGame(), PageRequest.of(0, 5));
        int betterScores = (int) topHighScores.stream().filter(gameScore -> gameScore.getTotalPayoff() > savedGameScore.getTotalPayoff()).count();
        switch (betterScores){
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 30;
            case 3:
                return 20;
            case 4:
                return 10;
            case 5:
                return 0;
            default:
                throw new NoSuchElementException("Unpredicted state. Should not happen. More high scores than expected.");
        }


    }

    private boolean isGameVerified(Game game) {
        return game.getVerificationStatus().getName().equals("VERIFIED") ||
                game.getVerificationStatus().getName().equals("WILDCARD_VERIFIED");
    }

    private Set<Payoff> findNashEquilibria(Game game) {
        Set<Payoff> result = new HashSet<>();

        Player player1 = game.getPlayers().stream().filter(
                player -> player.getName().equals("Player1")
        ).findFirst().orElseThrow(
                () -> new PlayerNotFoundException("Player with name not found.")
        );

        Player player2 = game.getPlayers().stream().filter(
                player -> player.getName().equals("Player2")
        ).findFirst().orElseThrow(
                () -> new PlayerNotFoundException("Player with name not found.")
        );

        List<Payoff> nashEqilibriaPlayer1Candidates = getNashEquilibiriaCandidates(player1, player2);;

        List<Payoff> nashEquilibriaPlayer2Candidates = getNashEquilibiriaCandidates(player2, player1);

        nashEqilibriaPlayer1Candidates.forEach(
                payoffPlayer1 -> nashEquilibriaPlayer2Candidates.forEach(
                        payoffPlayer2 -> {
                            if(payoffPlayer1.getPlayedStrategy().equals(payoffPlayer2.getOpposingStrategy())
                            && payoffPlayer2.getPlayedStrategy().equals(payoffPlayer1.getOpposingStrategy()))
                                result.add(payoffPlayer1);
                        }
                )
        );

        return result;
    }

    private List<Payoff> getNashEquilibiriaCandidates(Player player1, Player player2) {
        List<Payoff> result = new ArrayList<>();

        player2.getPlayableStrategies().forEach(strategy -> {
            Payoff maxPayoff = player1.getPayoffs().stream().filter(
                    payoff -> payoff.getOpposingStrategy().equals(strategy)
            ).max(Comparator.comparingDouble(Payoff::getAmount)).orElseThrow(
                    () -> new PayoffNotFoundException("Payoff list for player is empty")
            );
            result.addAll(player1.getPayoffs().stream().filter(payoff ->
                    payoff.getAmount() == maxPayoff.getAmount() && payoff.getOpposingStrategy().equals(strategy)).collect(Collectors.toList()));

        });

        return result;
    }


}
