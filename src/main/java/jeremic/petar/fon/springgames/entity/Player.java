package jeremic.petar.fon.springgames.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    @Transient
    Set<Strategy> playableStrategies;


    public Set<Strategy> getPlayableStrategies() {
        return playableStrategies;
    }

    @PostLoad
    public void setPlayableStrategies() {
        Set<Strategy> uniqueStrategies = new HashSet<>();
        this.getPayoffs().forEach(p -> uniqueStrategies.add(p.getPlayedStrategy()));
        this.playableStrategies = uniqueStrategies;
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

    public Player() {
    }
}
