package cinema.exception;

public class InvalidJwtAuthentication extends RuntimeException {
    public InvalidJwtAuthentication(String message, Throwable cause) {
        super(message, cause);
    }
}
