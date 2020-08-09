package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StrategyRepositoryTest {

    @Autowired
    private StrategyRepository strategyRepository;


    private List<Strategy> strategies;
    private List<Strategy> strategiesEmpty;
    private List<Strategy> strategiesOne;

    @BeforeEach
    void setUp() {
        strategies = new ArrayList<>();
        strategies.add(new Strategy("Strategy1"));
        strategies.add(new Strategy("Strategy2"));
        strategies.add(new Strategy("Strategy3"));

        strategiesEmpty = new ArrayList<>();

        strategiesOne = new ArrayList<>();
        strategiesOne.add(new Strategy("Strategy1"));

    }

    @Test
    void saveAll() {
        List<Strategy> strategiesCloned = new ArrayList<>();
        strategies.forEach(strategy -> strategiesCloned.add(new Strategy(strategy.getName())));

        List<Strategy> savedStrategies = strategyRepository.saveAll(strategies);

        assertNotNull(savedStrategies);
        assertIterableEquals(strategiesCloned.stream().flatMap(strategy -> Stream.of(strategy.getName())).collect(Collectors.toList()), savedStrategies.stream().flatMap(strategy -> Stream.of(strategy.getName())).collect(Collectors.toList()));

    }

    @AfterEach
    public void tearDown(){
        strategies = null;
        strategiesEmpty = null;
        strategiesOne = null;
    }
}