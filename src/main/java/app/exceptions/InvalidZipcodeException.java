package app.exceptions;

public class InvalidZipcodeException extends Exception {
    public InvalidZipcodeException(String message) {
        super(message);
    }
}
