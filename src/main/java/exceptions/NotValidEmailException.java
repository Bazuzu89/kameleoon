package exceptions;

public class NotValidEmailException extends Throwable {
    private String message;

    public NotValidEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
