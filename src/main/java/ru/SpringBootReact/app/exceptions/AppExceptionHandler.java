package ru.SpringBootReact.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = AppRequestException.class)
    public ResponseEntity<Object> handleAppRequestException(AppRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        AppException appException = new AppException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                badRequest
        );

        return new ResponseEntity<>(appException, badRequest);
    }
}
