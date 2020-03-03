package board.dao;

public class ExceptionHandler extends Exception {
    public ExceptionHandler() {
        super();
    }

    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}