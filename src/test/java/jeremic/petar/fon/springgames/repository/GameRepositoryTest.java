package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.Payoff;
import jeremic.petar.fon.springgames.entity.Player;
import jeremic.petar.fon.springgames.entity.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private StrategyRepository strategyRepository;

    private Game game;

    @BeforeEach
    void setUp() {
        game = createGame();
    }

    private Game createGame() {
        Game result = new Game();

        result.setName("GameTest");
        result.setDescription("DESC");
        result.setExternalInfo("INFO");

        Strategy s1 = new Strategy("S1");
        Strategy s2 = new Strategy("S2");

        List<Strategy> strategies = Stream.of(s1, s2).collect(Collectors.toList());
        result.setStrategies(strategies);

        Player playerRow = new Player();
        playerRow.setName("PlayerRow");

        Player playerColumn = new Player();
        playerColumn.setName("PlayerColumn");

        Payoff p11 = new Payoff(1, s1, s1);
        Payoff p12 = new Payoff(1, s1, s2);
        Payoff p13 = new Payoff(1, s2, s1);
        Payoff p14 = new Payoff(1, s2, s2);

        Payoff p21 = new Payoff(1, s1, s1);
        Payoff p22 = new Payoff(1, s1, s2);
        Payoff p23 = new Payoff(1, s2, s1);
        Payoff p24 = new Payoff(1, s2, s2);

        playerRow.setPayoffs(Stream.of(p11, p12, p13, p14).collect(Collectors.toList()));
        playerColumn.setPayoffs(Stream.of(p21, p22, p23, p24).collect(Collectors.toList()));

        result.setPlayers(Stream.of(playerRow, playerColumn).collect(Collectors.toList()));

        return result;
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void findById() {
        Long expectedGameId = 1L;

        Game game = gameRepository.findById(1L).get();

        assertEquals(expectedGameId, game.getId());
    }

    @Test
    void findByIdException() {
        assertThrows(NoSuchElementException.class,() -> gameRepository.findById(-1L).get());
    }

    @Test
    void findByName() {
        String expectedName = "Coordination";

        Game game = gameRepository.findByName("Coordination").get();

        assertEquals(expectedName, game.getName());
    }

    @Test
    void findByNameException() {
        assertThrows(NoSuchElementException.class,() -> gameRepository.findByName("").get());
    }

    @Test
    void save() {
        Game expectedGame = createGame();

        List<Strategy> savedStrategies = strategyRepository.saveAll(game.getStrategies());

        game.setStrategies(savedStrategies);

        game.getPlayers()
                .forEach(player -> player.getPayoffs()
                        .forEach(payoff -> savedStrategies
                                .forEach(strategy -> {if(strategy.getName().equals(payoff.getPlayedStrategy().getName()))
                                    payoff.setPlayedStrategy(strategy);
                                    if(strategy.getName().equals(payoff.getOpposingStrategy().getName()))
                                        payoff.setOpposingStrategy(strategy);  } )));



        Game savedGame = gameRepository.save(game);

        assertEquals(expectedGame, savedGame);
    }

    @Test
    void findAll() {

        List<String> expectedGameNames = Stream.of("Coordination","Prisoner's Dillema","Custom game","Rata","Deer","CustomGame1").collect(Collectors.toList());

        List<Game> games = gameRepository.findAll();

        assertNotNull(games);
        assertEquals(6, games.size());
        assertIterableEquals(expectedGameNames, games.stream().flatMap(game1 -> Stream.of(game1.getName())).collect(Collectors.toList()));
    }
}