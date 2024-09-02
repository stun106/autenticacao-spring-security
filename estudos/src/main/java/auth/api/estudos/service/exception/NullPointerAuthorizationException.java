package auth.api.estudos.service.exception;

public class NullPointerAuthorizationException extends RuntimeException {
    public NullPointerAuthorizationException (String message) {
        super(message);
    }
}
