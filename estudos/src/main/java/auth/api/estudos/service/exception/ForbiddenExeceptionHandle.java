package auth.api.estudos.service.exception;

public class ForbiddenExeceptionHandle extends RuntimeException{
    public ForbiddenExeceptionHandle (String message) {
        super(message);
    }
}
