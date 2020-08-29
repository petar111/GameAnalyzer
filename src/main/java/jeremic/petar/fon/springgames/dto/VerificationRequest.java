package jeremic.petar.fon.springgames.dto;


public class VerificationRequest {
    private Long gameId;
    private Long userId;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
