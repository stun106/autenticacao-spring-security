package auth.api.estudos.service.exception;

public class UniqueViolationExeception extends RuntimeException{
    public UniqueViolationExeception (String message) {
        super(message);
    }
}
