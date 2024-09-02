package auth.api.estudos.service.exception;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException (String message) {
        super(message);
    }
}
