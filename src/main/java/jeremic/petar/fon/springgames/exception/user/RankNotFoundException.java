package jeremic.petar.fon.springgames.exception.user;

public class RankNotFoundException extends RuntimeException {
    public RankNotFoundException(String rank_between_not_found) {
        super(rank_between_not_found);
    }
}
