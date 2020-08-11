package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.dto.UserDto;

public class GameSessionInfoDto {
    private Long id;
    private int numberOfRounds;
    private GameInfoDto game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public GameInfoDto getGame() {
        return game;
    }

    public void setGame(GameInfoDto game) {
        this.game = game;
    }
}
