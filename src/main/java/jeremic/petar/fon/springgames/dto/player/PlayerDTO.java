package jeremic.petar.fon.springgames.dto.player;

import jeremic.petar.fon.springgames.dto.PayoffDTO;
import jeremic.petar.fon.springgames.dto.StrategyDTO;
import jeremic.petar.fon.springgames.entity.Payoff;

import java.util.List;
import java.util.Set;

public class PlayerDTO {
    private Long id;
    private String name;

    private List<PayoffDTO> payoffs;

    private Set<StrategyDTO> playableStrategies;

    public Set<StrategyDTO> getPlayableStrategies() {
        return playableStrategies;
    }

    public void setPlayableStrategies(Set<StrategyDTO> playableStrategies) {
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

    public List<PayoffDTO> getPayoffs() {
        return payoffs;
    }

    public void setPayoffs(List<PayoffDTO> payoffs) {
        this.payoffs = payoffs;
    }
}
