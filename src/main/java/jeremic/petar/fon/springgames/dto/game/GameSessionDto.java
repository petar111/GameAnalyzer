package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.dto.player.PlayerMatchDto;
import jeremic.petar.fon.springgames.dto.UserDto;

import java.util.List;
import java.util.Map;

public class GameSessionDto {
    private Long id;
    private UserDto creator;
    private int numberOfRounds;
    private GameDTO game;
    private List<PlayerMatchDto> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public List<PlayerMatchDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerMatchDto> players) {
        this.players = players;
    }
}
