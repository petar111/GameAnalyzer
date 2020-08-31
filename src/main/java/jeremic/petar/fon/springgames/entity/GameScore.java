package jeremic.petar.fon.springgames.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "game_score")
public class GameScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_payoff")
    private double totalPayoff;
    @Column(name = "number_of_rounds")
    private int numberOfRounds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPayoff() {
        return totalPayoff;
    }

    public void setTotalPayoff(double totalPayoff) {
        this.totalPayoff = totalPayoff;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameScore)) return false;
        GameScore gameScore = (GameScore) o;
        return Double.compare(gameScore.getTotalPayoff(), getTotalPayoff()) == 0 &&
                getNumberOfRounds() == gameScore.getNumberOfRounds() &&
                getUser().equals(gameScore.getUser()) &&
                getGame().equals(gameScore.getGame());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalPayoff(), getNumberOfRounds(), getUser(), getGame());
    }
}
