package jeremic.petar.fon.springgames.exception.user;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String s) {
        super(s);
    }
}
