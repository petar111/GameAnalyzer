package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.entity.Payoff;

import java.util.List;
import java.util.Set;

public class GameAdviceDto {
    private Set<Payoff> nashEquilibria;

    public Set<Payoff> getNashEquilibria() {
        return nashEquilibria;
    }

    public void setNashEquilibria(Set<Payoff> nashEquilibria) {
        this.nashEquilibria = nashEquilibria;
    }
}
