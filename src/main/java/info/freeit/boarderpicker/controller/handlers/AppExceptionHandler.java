package info.freeit.boarderpicker.controller.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @Autowired
    private ObjectErrorResponse userErrorResponse;

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponse> handleNotFoundException(RuntimeException e) {
        userErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        userErrorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        userErrorResponse.setStatus(HttpStatus.CONFLICT.value());
        userErrorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.CONFLICT);
    }
}

