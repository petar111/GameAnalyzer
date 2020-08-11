package jeremic.petar.fon.springgames.entity;

import javax.persistence.*;

@Entity
@Table(name = "game_session_player_strategy")
public class PlayedStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "times_played")
    private int timesPlayed;

    @ManyToOne
    @JoinColumn(name = "strategy_id")
    private Strategy strategy;

    public PlayedStrategy() {
    }

    public PlayedStrategy(int timesPlayed, Strategy strategy) {
        this.strategy = strategy;
        this.timesPlayed = timesPlayed;
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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
