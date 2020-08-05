package jeremic.petar.fon.springgames.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "player_configuration")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_player_configuration")
    List<Payoff> payoffs;


    @ManyToMany
            @JoinTable(
                    name = "payoff",
                    joinColumns = @JoinColumn(name = "id_player_configuration"),
                    inverseJoinColumns = @JoinColumn(name = "id_strategy")
            )
    Set<Strategy> playableStrategies;


    public Set<Strategy> getPlayableStrategies() {
        return playableStrategies;
    }

    public void setPlayableStrategies(Set<Strategy> playableStrategies) {
        this.playableStrategies = playableStrategies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payoff> getPayoffs() {
        return payoffs;
    }

    public void setPayoffs(List<Payoff> payoffs) {
        this.payoffs = payoffs;
    }
}
