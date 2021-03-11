package spring.practice.project.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.exception.*;
import spring.practice.project.response.Response;

import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = {"spring.practice.project.user"})
public class UserExceptionAdvice {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Response> handleDupIdExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Duplicate user id"));
    }

    @ExceptionHandler(DuplicateNickException.class)
    public ResponseEntity<Response> handleDupNickExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Duplicate user nick"));
    }

    @ExceptionHandler(NoEqualsPassword2ConfirmPasswordException.class)
    public ResponseEntity<Response> handleNoEqualsPw2ConfirmPwExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("No match password with confirm password"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidJSONFormatExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid JSON format"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleBindData(MethodArgumentNotValidException ex) {
        String errorCodes = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getCodes()[0])
                .collect(Collectors.joining("."));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("errorCodes = " + errorCodes));
    }

}
