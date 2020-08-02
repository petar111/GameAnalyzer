package jeremic.petar.fon.springgames.dto;

import jeremic.petar.fon.springgames.entity.Strategy;
import lombok.Data;

@Data
public class PayoffDTO {
    private Long id;
    private double amount;
    private Strategy playedStrategy;
    private Strategy opposingStrategy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Strategy getPlayedStrategy() {
        return playedStrategy;
    }

    public void setPlayedStrategy(Strategy playedStrategy) {
        this.playedStrategy = playedStrategy;
    }

    public Strategy getOpposingStrategy() {
        return opposingStrategy;
    }

    public void setOpposingStrategy(Strategy opposingStrategy) {
        this.opposingStrategy = opposingStrategy;
    }
}
