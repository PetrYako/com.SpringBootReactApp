package ru.SpringBootReact.app.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class AppException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public AppException(String message,
                        ZonedDateTime zonedDateTime,
                        HttpStatus httpStatus) {
        this.message = message;
        this.zonedDateTime = zonedDateTime;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
