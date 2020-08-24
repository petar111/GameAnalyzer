package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.dto.UserDto;
import jeremic.petar.fon.springgames.entity.Game;
import jeremic.petar.fon.springgames.entity.User;

public class GameScoreDto {

    private Long id;
    private double totalPayoff;
    private int numberOfRounds;
    private UserDto user;
    private GameDTO game;

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }
}
