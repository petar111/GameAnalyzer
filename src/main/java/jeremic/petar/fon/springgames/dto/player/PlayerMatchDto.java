package jeremic.petar.fon.springgames.dto.player;

import jeremic.petar.fon.springgames.dto.PlayedStrategyDto;
import jeremic.petar.fon.springgames.dto.StrategyDTO;
import jeremic.petar.fon.springgames.dto.player.PlayerDTO;

import java.util.List;
import java.util.Map;

public class PlayerMatchDto {

    private Long id;
    private PlayerDTO player;
    private int totalPayoff;
    private StrategyDTO selectedStrategy;
    private String playerLabel;
    private List<PlayedStrategyDto> playedStrategies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerLabel() {
        return playerLabel;
    }

    public void setPlayerLabel(String playerLabel) {
        this.playerLabel = playerLabel;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public int getTotalPayoff() {
        return totalPayoff;
    }

    public void setTotalPayoff(int totalPayoff) {
        this.totalPayoff = totalPayoff;
    }

    public StrategyDTO getSelectedStrategy() {
        return selectedStrategy;
    }

    public void setSelectedStrategy(StrategyDTO selectedStrategy) {
        this.selectedStrategy = selectedStrategy;
    }

    public List<PlayedStrategyDto> getPlayedStrategies() {
        return playedStrategies;
    }

    public void setPlayedStrategies(List<PlayedStrategyDto> playedStrategies) {
        this.playedStrategies = playedStrategies;
    }
}
