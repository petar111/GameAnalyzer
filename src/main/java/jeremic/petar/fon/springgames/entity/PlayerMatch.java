package jeremic.petar.fon.springgames.entity;



import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game_session_player")
public class PlayerMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_payoff")
    private int totalPayoff;

    @Column(name = "player_label")
    private String playerLabel;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "selected_strategy_id")
    private Strategy selectedStrategy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_session_player_id")
    private List<PlayedStrategy> playedStrategies;

    public String getPlayerLabel() {
        return playerLabel;
    }

    public void setPlayerLabel(String playerLabel) {
        this.playerLabel = playerLabel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalPayoff() {
        return totalPayoff;
    }

    public void setTotalPayoff(int totalPayoff) {
        this.totalPayoff = totalPayoff;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Strategy getSelectedStrategy() {
        return selectedStrategy;
    }

    public void setSelectedStrategy(Strategy selectedStrategy) {
        this.selectedStrategy = selectedStrategy;
    }

    public List<PlayedStrategy> getPlayedStrategies() {
        return playedStrategies;
    }

    public void setPlayedStrategies(List<PlayedStrategy> playedStrategies) {
        this.playedStrategies = playedStrategies;
    }
}
