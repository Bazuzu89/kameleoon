package exceptions;

public class NotUniqueException extends Throwable {
        private final String message;


    public NotUniqueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
