package spring.practice.project.domain;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.user.NotFoundException;
import spring.practice.project.response.Response;

import java.net.ConnectException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundExp() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response("No data requested"));
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
    public ResponseEntity<Response> handleBindData(MethodArgumentNotValidException ex) {
        String errorCodes = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getCodes()[0])
                .collect(Collectors.joining("."));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("errorCodes : " + errorCodes));
    }

}
