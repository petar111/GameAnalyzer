package jeremic.petar.fon.springgames.dto;

public class HttpResponseBody<T> {
    private String signal;
    private String message;
    private T responseObject;


    public HttpResponseBody(String signal, String message, T responseObject) {
        this.signal = signal;
        this.message = message;
        this.responseObject = responseObject;
    }

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

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }
}
