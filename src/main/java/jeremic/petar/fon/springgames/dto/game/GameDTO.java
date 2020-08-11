package jeremic.petar.fon.springgames.dto.game;


import jeremic.petar.fon.springgames.dto.player.PlayerDTO;
import jeremic.petar.fon.springgames.dto.StrategyDTO;
import jeremic.petar.fon.springgames.dto.UserDto;

import java.util.List;

public class GameDTO {
    private Long id;
    private UserDto creator;
    private String name;
    private String externalInfo;
    private String description;

    private List<StrategyDTO> strategies;
    private List<PlayerDTO> players;

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public String getExternalInfo() {
        return externalInfo;
    }

    public void setExternalInfo(String externalInfo) {
        this.externalInfo = externalInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<StrategyDTO> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<StrategyDTO> strategies) {
        this.strategies = strategies;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
