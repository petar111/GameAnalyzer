package jeremic.petar.fon.springgames.dto;

import jeremic.petar.fon.springgames.entity.Strategy;


public class PlayedStrategyDto {
    private Long id;
    private int timesPlayed;
    private StrategyDTO strategy;

    public PlayedStrategyDto() {
    }

    public PlayedStrategyDto(Long id, int timesPlayed, StrategyDTO strategy) {
        this.id = id;
        this.timesPlayed = timesPlayed;
        this.strategy = strategy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public StrategyDTO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyDTO strategy) {
        this.strategy = strategy;
    }
}
