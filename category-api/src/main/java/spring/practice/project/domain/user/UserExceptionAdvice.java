package spring.practice.project.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.practice.project.domain.user.exception.DuplicateNickException;
import spring.practice.project.domain.user.exception.DuplicateUserException;
import spring.practice.project.domain.user.exception.NoEqualsPassword2ConfirmPasswordException;
import spring.practice.project.domain.user.exception.WrongIdPasswordException;
import spring.practice.project.response.Response;

@RestControllerAdvice
public class UserExceptionAdvice {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Response> handleDupIdExp() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Response("Duplicate user id"));
    }

    @ExceptionHandler(DuplicateNickException.class)
    public ResponseEntity<Response> handleDupNickExp() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new Response("Duplicate user nick"));
    }

    @ExceptionHandler(NoEqualsPassword2ConfirmPasswordException.class)
    public ResponseEntity<Response> handleNoEqualsPw2ConfirmPwExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("No match password with confirm password"));
    }

    @ExceptionHandler(WrongIdPasswordException.class)
    public ResponseEntity<Response> handleWrongIdPwExp() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Response("Invalid id or password"));
    }

}