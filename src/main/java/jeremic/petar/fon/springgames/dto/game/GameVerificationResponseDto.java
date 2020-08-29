package jeremic.petar.fon.springgames.dto.game;

import jeremic.petar.fon.springgames.dto.UserDto;

public class GameVerificationResponseDto {
    private String signal;
    private String message;
    private GameInfoDto game;
    private UserDto user;

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GameInfoDto getGame() {
        return game;
    }

    public void setGame(GameInfoDto game) {
        this.game = game;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
