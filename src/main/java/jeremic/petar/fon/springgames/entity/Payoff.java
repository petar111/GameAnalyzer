package jeremic.petar.fon.springgames.entity;


import lombok.Data;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

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
