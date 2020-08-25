package jeremic.petar.fon.springgames.exception;


public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String game_with_id_not_found) {
        super(game_with_id_not_found);
    }
}
