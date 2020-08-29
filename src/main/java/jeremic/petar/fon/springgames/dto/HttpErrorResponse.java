package jeremic.petar.fon.springgames.dto;

import org.springframework.http.HttpStatus;

public class HttpErrorResponse {
    private int statusCode;
    private HttpStatus status;
    private String reason;
    private String message;
}
