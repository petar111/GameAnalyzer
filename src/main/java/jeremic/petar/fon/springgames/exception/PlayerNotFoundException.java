package jeremic.petar.fon.springgames.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message){
        super(message);
    }
}
