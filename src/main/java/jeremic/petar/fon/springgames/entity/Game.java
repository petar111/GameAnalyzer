package jeremic.petar.fon.springgames.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "external_info")
    private String externalInfo;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
            @JoinColumn(name = "game_id")
    List<Strategy> strategies;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
        @JoinColumn(name = "game_id")
    List<Player> players;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExternalInfo() {
        return externalInfo;
    }

    public String getDescription() {
        return description;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExternalInfo(String externalInfo) {
        this.externalInfo = externalInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getName().equals(game.getName()) &&
                getExternalInfo().equals(game.getExternalInfo()) &&
                getDescription().equals(game.getDescription()) &&
                Objects.equals(getStrategies(), game.getStrategies()) &&
                Objects.equals(getPlayers(), game.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExternalInfo(), getDescription(), getStrategies(), getPlayers());
    }
}
