package spring.practice.project.domain.global;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.global.exception.InvalidApproachException;
import spring.practice.project.domain.global.exception.NotFoundException;
import spring.practice.project.domain.global.response.Response;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundExp() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("No data requested"));
    }

    @ExceptionHandler({InvalidApproachException.class, NullPointerException.class})
    public ResponseEntity<Response> handleInvalidApproachExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid Approach"));
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<Response> handleTypeMismatch() {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new Response("Type Mismatch"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidJSONFormatExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid JSON Format"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindData(MethodArgumentNotValidException ex) {
        String errorCodes = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining("."));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("errorCodes : " + errorCodes);
    }

}
