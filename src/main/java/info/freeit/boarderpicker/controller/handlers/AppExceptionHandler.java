package info.freeit.boarderpicker.controller.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler {
    private final ObjectErrorResponse userErrorResponse;

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

    @ExceptionHandler
    public ResponseEntity<ObjectErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        userErrorResponse.setStatus(HttpStatus.FORBIDDEN.value());
        userErrorResponse.setMessage("Access is only for admin");
        return new ResponseEntity<>(userErrorResponse, HttpStatus.FORBIDDEN);
    }
}

