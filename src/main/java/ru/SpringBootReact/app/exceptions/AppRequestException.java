package ru.SpringBootReact.app.exceptions;

public class AppRequestException extends RuntimeException {

    public AppRequestException(String message) {
        super(message);
    }

    public AppRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
