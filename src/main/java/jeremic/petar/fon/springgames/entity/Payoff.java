package jeremic.petar.fon.springgames.entity;


import lombok.Data;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payoff")
public class Payoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_strategy")
    private Strategy playedStrategy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_opposing_strategy")
    private Strategy opposingStrategy;

    public Payoff() {
    }

    public Payoff(double amount, Strategy playedStrategy, Strategy opposingStrategy) {
        this.amount = amount;
        this.playedStrategy = playedStrategy;
        this.opposingStrategy = opposingStrategy;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payoff)) return false;
        Payoff payoff = (Payoff) o;
        return Double.compare(payoff.getAmount(), getAmount()) == 0 &&
                getPlayedStrategy().equals(payoff.getPlayedStrategy()) &&
                getOpposingStrategy().equals(payoff.getOpposingStrategy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getPlayedStrategy(), getOpposingStrategy());
    }
}
